package net.kiberion.swampmachine.scripting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.entities.TestCaster;
import net.kiberion.utils.StopWatch;

public abstract class AbstractScriptTest {

    private static final Logger log = LogManager.getLogger();

    protected abstract ScriptEntityFactory getEntityFactory();

    protected abstract Path getPathToTestResourcres();

    private volatile AssertionError thrownException;

    @Test
    public void testThreadSafety() {

        final AtomicInteger counter = new AtomicInteger();
        
        log.info("==========================");
        log.info("Start multithreaded run with: " + getEntityFactory().getClass().getSimpleName());
        StopWatch stopWatchMultithreaded = new StopWatch();

        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<SwampScript> scripts = prepareScripts(true);
        stopWatchMultithreaded.start();

        final int runs = 100; 
        for (int i = 0; i < runs; i++) {
            Runnable worker = () -> {
                counter.incrementAndGet();
                try {
                    invokeScriptInternal(false, scripts);
                } catch (Throwable e) {
                    thrownException = new AssertionError(e);
                }
            };
            executor.execute(worker);
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        stopWatchMultithreaded.endAndLog("Multithreaded run");
        System.out.println("\nFinished all threads");

        if (thrownException != null) {
            throw new AssertionError(thrownException.getMessage(), thrownException);
        }
        assertEquals (runs, counter.get());
    }

    @Test
    public void testInvokeScript() {
        List<SwampScript> scripts = prepareScripts(true);
        invokeScriptInternal(true, scripts);
    }

    private List<SwampScript> prepareScripts(boolean writeLogs) {
        if (writeLogs) {
            log.info("==========================");
            log.info("Start run with: " + getEntityFactory().getClass().getSimpleName());
        }

        StopWatch stopWatchInit = new StopWatch();
        if (writeLogs) {
            stopWatchInit.start();
        }

        AbstractScriptParser invoker = getEntityFactory().getParserInstance();
        List<SwampScript> scripts = invoker.parseScriptsFromPath(getPathToTestResourcres());
        if (writeLogs) {
            // Measure script initialization time
            stopWatchInit.endAndLog("Initialization");
        }

        assertEquals(1, scripts.size());

        return scripts;
    }

    private void invokeScriptInternal(boolean writeLogs, List<SwampScript> scripts) {
        StopWatch stopWatchBinding = new StopWatch();
        StopWatch stopWatchExecution = new StopWatch();
        StopWatch stopWatchResultProcessing = new StopWatch();

        TestCaster caster = new TestCaster();
        assertEquals(0, caster.getSaidMoo());
        assertEquals(0, caster.getMutableNumber().intValue());

        if (writeLogs) {
            stopWatchBinding.start();
        }
        SwampBinding params = getEntityFactory().getBindingInstance();
        params.setVariable("x", 1);
        params.setVariable("caster", caster);
        if (writeLogs) {
            // Measure script binding time
            stopWatchBinding.endAndLog("Binding");
        }
        SwampScript script = scripts.get(0);
        if (writeLogs) {
            stopWatchExecution.start();
        }
        SwampScriptInvokationResult result = script.invoke(params);

        if (writeLogs) {
            // Measure script execution time
            stopWatchExecution.endAndLog("Execution");
        }
        assertNotNull(result);

        if (writeLogs) {
            stopWatchResultProcessing.start();
        }
        assertEquals(Integer.valueOf(5), result.getVariableValue("x"));
        assertEquals(1, caster.getSaidMoo());
        assertEquals(1, caster.getMutableNumber().intValue());
        assertEquals(true, result.getVariableValue("result"));
        if (writeLogs) {
            // Measure script result processing time
            stopWatchResultProcessing.endAndLog("Result processing");
        }
    }
}
