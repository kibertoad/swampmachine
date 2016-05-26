package net.kiberion.swampmachine.groovy;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

import org.apache.commons.lang3.Validate;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import net.kiberion.swampmachine.entities.common.api.ParametrizedInvokable;

/**
 * @author kibertoad
 */
public class GroovyScript implements ParametrizedInvokable<Binding> {

    private static final int POOL_SIZE = 5;

    private String scriptCode;
    private BlockingDeque<Script> instancePool = new LinkedBlockingDeque<>(POOL_SIZE);

    public GroovyScript() {
    }

    public GroovyScript(String scriptCode) {
        setScriptCode(scriptCode);
    }

    protected void setScriptCode(String toCode) {
        GroovyShell shell = new GroovyShell();
        scriptCode = toCode;

        IntStream.rangeClosed(1, POOL_SIZE).forEach(counter -> {
            instancePool.push(shell.parse(scriptCode));
        });

    }

    @Override
    public void invoke(Binding binding) {
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
        } finally {
            instancePool.push(script);
        }
    }

    @Override
    public String toString() {
        return scriptCode;
    }

}
