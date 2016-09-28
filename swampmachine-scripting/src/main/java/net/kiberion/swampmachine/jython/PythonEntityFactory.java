package net.kiberion.swampmachine.jython;

import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;

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
