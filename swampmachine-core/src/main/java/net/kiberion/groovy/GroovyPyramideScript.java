package net.kiberion.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import net.kiberion.entities.common.api.Invokable;

/**
 * @author kibertoad
 */
public class GroovyPyramideScript implements Invokable {

    private String scriptCode;
    private Script compiledScript;
    private GroovyShell shell;

    public GroovyPyramideScript() {
    }
    
    public GroovyPyramideScript(String scriptCode) {
        shell = new GroovyShell();
        setScriptCode(scriptCode);
    }

    protected void setScriptCode(String toCode) {
        scriptCode = toCode;
        compiledScript = shell.parse(scriptCode);
    }

    /**
     * Pass prepared binding context from processor to script
     * @param binding
     */
    public void setBinding(Binding binding) {
        compiledScript.setBinding(binding);
    }

    @Override
    public void invoke() {
        compiledScript.run();
    }

    @Override
    public String toString() {
        return scriptCode;
    }

    public Script getCompiledScript() {
        return compiledScript;
    }
}
