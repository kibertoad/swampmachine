package net.kiberion.swampmachine.jruby;

import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;

public class RubyEntityFactory implements ScriptEntityFactory{

    private static final RubyScriptParser invoker = new RubyScriptParser();
    
    @Override
    public SwampBinding getBindingInstance() {
        return new RubyBinding();
    }

    @Override
    public AbstractScriptParser getParserInstance() {
        return invoker;
    }
    
}
