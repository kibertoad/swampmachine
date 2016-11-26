package net.kiberion.swampmachine.scripting.jython;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.common.AbstractScriptParser;

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
