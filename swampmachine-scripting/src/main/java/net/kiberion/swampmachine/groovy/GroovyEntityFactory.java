package net.kiberion.swampmachine.groovy;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;
import net.kiberion.swampmachine.scripting.SwampBinding;

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
