package net.kiberion.swampmachine.scripting.jruby;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.common.AbstractScriptParser;

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
