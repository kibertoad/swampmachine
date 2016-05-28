package net.kiberion.swampmachine.scripting;

public interface SwampScriptInvokationResult {

    public <T> T getVariableValue(String varName);
    
}
