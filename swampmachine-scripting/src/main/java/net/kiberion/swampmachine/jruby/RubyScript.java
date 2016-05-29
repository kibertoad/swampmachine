package net.kiberion.swampmachine.jruby;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.kiberion.swampmachine.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.SwampScript;
import net.kiberion.swampmachine.scripting.SwampScriptInvokationResult;

public class RubyScript implements SwampScript {

    private static final ScriptEngineManager engineManager = new ScriptEngineManager();

    private final CompiledScript script;

    public RubyScript(CompiledScript compiledScript) {
        this.script = compiledScript;
    }

    @Override
    public SwampScriptInvokationResult invoke(SwampBinding param) {
        RubyBinding rubyBinding = (RubyBinding) param;
        try {
            ScriptEngine engine = engineManager.getEngineByName("jruby");
            ScriptContext context = engine.getContext();
            context.setBindings(rubyBinding, ScriptContext.ENGINE_SCOPE);
            script.eval(context);
        } catch (ScriptException e) {
            throw new IllegalStateException(e);
        }

        return new RubyResultWrapper(rubyBinding);
    }

}
