package net.kiberion.swampmachine.scripting.jruby;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.api.scripting.SwampScript;
import net.kiberion.swampmachine.api.scripting.SwampScriptInvokationResult;

//ToDo As tests show, currently this implementation is not thread-safe
public class RubyScript implements SwampScript {

    private static final ScriptEngineManager engineManager = new ScriptEngineManager();

    private final CompiledScript script;

    public RubyScript(CompiledScript compiledScript) {
        this.script = compiledScript;
    }

    @Override
    public SwampScriptInvokationResult invoke(SwampBinding param) {
        RubyBinding rubyBinding = new RubyBinding((RubyBinding) param);
        try {
            ScriptEngine engine = engineManager.getEngineByName("jruby");
            ScriptContext context = engine.getContext();
            context.setBindings(rubyBinding, ScriptContext.ENGINE_SCOPE);
            Object resultObject = script.eval(context);
            rubyBinding.put(SCRIPT_RESULT, resultObject);
        } catch (ScriptException e) {
            throw new IllegalStateException(e);
        }

        return new RubyResultWrapper(rubyBinding);
    }

}
