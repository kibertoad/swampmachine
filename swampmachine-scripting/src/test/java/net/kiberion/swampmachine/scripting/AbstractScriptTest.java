package net.kiberion.swampmachine.scripting;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.List;

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
    
    @Test
    public void testInvoker () {
        log.info("==========================");
        log.info("Start run with: "+getEntityFactory().getClass().getSimpleName());
        StopWatch stopWatchInit = new StopWatch();
        StopWatch stopWatchBinding = new StopWatch();
        StopWatch stopWatchExecution = new StopWatch();
        StopWatch stopWatchResultProcessing = new StopWatch();

        stopWatchInit.start();
        AbstractScriptParser invoker = getEntityFactory().getParserInstance();
        List<SwampScript> scripts = invoker.parseScriptsFromPath(getPathToTestResourcres());
        stopWatchInit.endAndLog("Initialization"); //Measure script initialization time

        assertEquals (1, scripts.size());
        TestCaster caster = new TestCaster();
        assertEquals (0, caster.getSaidMoo());
        assertEquals (0, caster.getMutableNumber().intValue());

        stopWatchBinding.start();
        SwampBinding params = getEntityFactory().getBindingInstance();
        params.setVariable("x", 1);
        params.setVariable("caster", caster);
        stopWatchBinding.endAndLog("Binding"); //Measure script binding time
        
        SwampScript script = scripts.get(0);
        stopWatchExecution.start();
        SwampScriptInvokationResult result = script.invoke(params);
        stopWatchExecution.endAndLog("Execution"); //Measure script execution time
        assertNotNull (result);

        stopWatchResultProcessing.start();
        assertEquals (Integer.valueOf(2), result.getVariableValue("x"));
        assertEquals (1, caster.getSaidMoo());
        assertEquals (1, caster.getMutableNumber().intValue());
        assertEquals (true, result.getVariableValue("result"));
        stopWatchResultProcessing.endAndLog("Result processing"); //Measure script result processing time
    }      
    
}
