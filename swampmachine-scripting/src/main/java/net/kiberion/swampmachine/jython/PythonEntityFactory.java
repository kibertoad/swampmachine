package net.kiberion.swampmachine.jython;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;
import net.kiberion.swampmachine.scripting.SwampBinding;

public class PythonEntityFactory implements ScriptEntityFactory{

    private static final PythonScriptParser invoker = new PythonScriptParser();
    
    @Override
    public SwampBinding getBindingInstance() {
        return new PythonBinding();
    }

    @Override
    public AbstractScriptParser getParserInstance() {
        return invoker;
    }
    
}
