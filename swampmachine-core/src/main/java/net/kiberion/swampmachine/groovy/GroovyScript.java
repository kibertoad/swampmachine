package net.kiberion.swampmachine.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import net.kiberion.swampmachine.entities.common.api.Invokable;

/**
 * @author kibertoad
 */
public class GroovyScript implements Invokable {

    private String scriptCode;
    private Script compiledScript;

    public GroovyScript() {
    }
    
    public GroovyScript(String scriptCode) {
        setScriptCode(scriptCode);
    }

    protected void setScriptCode(String toCode) {
        GroovyShell shell = new GroovyShell();
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
