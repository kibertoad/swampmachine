package net.kiberion.swampmachine.scripting.groovy;

import java.io.Reader;
import java.util.HashMap;

import org.apache.commons.lang3.Validate;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.api.scripting.SwampScript;
import net.kiberion.swampmachine.api.scripting.SwampScriptInvokationResult;

/**
 * This class is thread-safe
 * 
 * @author kibertoad
 *
 */
public class GroovyScript implements SwampScript {

    private Script compiledScript;

    public GroovyScript() {
    }

    public GroovyScript(String script) {
        GroovyShell shell = new GroovyShell();
        compiledScript = shell.parse(script);
    }

    public GroovyScript(Reader script) {
        GroovyShell shell = new GroovyShell();
        compiledScript = shell.parse(script);
    }

    @Override
    public SwampScriptInvokationResult invoke(SwampBinding binding) {
        Validate.notNull(binding, "Binding cannot be null.");
        GroovyInvokationResult result = new GroovyInvokationResult();
        try {
            Script invokableScript = compiledScript.getClass().newInstance();
            invokableScript.setBinding((GroovyBinding) binding);
            Object resultObject = invokableScript.run();
            result.setVariables(new HashMap<>(binding.getVariableMap()));
            result.getVariables().put(SCRIPT_RESULT, resultObject);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return result;
    }

}
