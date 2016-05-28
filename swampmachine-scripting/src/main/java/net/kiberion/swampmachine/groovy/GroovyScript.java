package net.kiberion.swampmachine.groovy;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

import org.apache.commons.lang3.Validate;

import com.google.common.io.CharStreams;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import net.kiberion.swampmachine.scripting.SwampScript;

/**
 * @author kibertoad
 */
public class GroovyScript implements SwampScript<GroovyBinding, GroovyInvokationResult> {

    private static final int POOL_SIZE = 5;

    private BlockingDeque<Script> instancePool = new LinkedBlockingDeque<>(POOL_SIZE);

    public GroovyScript() {
    }

    public GroovyScript(String script) {
        GroovyShell shell = new GroovyShell();
        IntStream.rangeClosed(1, POOL_SIZE).forEach(counter -> {
            instancePool.push(shell.parse(script));
        });
    }

    public GroovyScript(Reader script) {
        try {
            final String scriptCode = CharStreams.toString(script);
            GroovyShell shell = new GroovyShell();
            IntStream.rangeClosed(1, POOL_SIZE).forEach(counter -> {
                instancePool.push(shell.parse(scriptCode));
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public GroovyInvokationResult invoke(GroovyBinding binding) {
        GroovyInvokationResult result = new GroovyInvokationResult();
        Validate.notNull(binding, "Binding cannot be null.");
        Script script;
        try {
            script = instancePool.takeFirst();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        try {
            script.setBinding(binding);
            script.run();
            result.setVariables(new HashMap<String, Object>(binding.getVariables()));
        } finally {
            instancePool.push(script);
        }

        return result;
    }

}
