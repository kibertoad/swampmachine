package net.kiberion.swampmachine.factories;

import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;

public interface ScriptEntityFactory {

    public SwampBinding getBindingInstance ();
    public AbstractScriptParser getParserInstance();
    
}
