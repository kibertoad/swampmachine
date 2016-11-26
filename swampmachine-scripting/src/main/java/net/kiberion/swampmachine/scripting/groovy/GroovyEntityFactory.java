package net.kiberion.swampmachine.scripting.groovy;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.common.AbstractScriptParser;

public class GroovyEntityFactory implements ScriptEntityFactory{

    private static final GroovyScriptParser invoker = new GroovyScriptParser();
    
    @Override
    public SwampBinding getBindingInstance() {
        return new GroovyBinding();
    }
    
    @Override
    public AbstractScriptParser getParserInstance() {
        return invoker;
    }
    
}
