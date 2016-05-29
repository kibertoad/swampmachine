package net.kiberion.swampmachine.jruby;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;
import net.kiberion.swampmachine.scripting.SwampBinding;

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
