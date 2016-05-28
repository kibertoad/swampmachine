package net.kiberion.swampmachine.factories;

import net.kiberion.swampmachine.scripting.AbstractScriptParser;
import net.kiberion.swampmachine.scripting.SwampBinding;

public interface ScriptEntityFactory {

    public SwampBinding getBindingInstance ();
    public AbstractScriptParser getParserInstance();
    
}
