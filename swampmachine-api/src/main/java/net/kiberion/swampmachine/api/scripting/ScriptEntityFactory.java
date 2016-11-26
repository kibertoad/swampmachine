package net.kiberion.swampmachine.api.scripting;

public interface ScriptEntityFactory {

    public SwampBinding getBindingInstance ();
    public ScriptParser getParserInstance();
    
}
