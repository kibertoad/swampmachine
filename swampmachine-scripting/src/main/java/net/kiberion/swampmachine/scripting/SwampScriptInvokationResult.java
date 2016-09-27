package net.kiberion.swampmachine.scripting;

public interface SwampScriptInvokationResult {

    public <T> T getVariableValue(String varName);

    public default <T> T getResult() {
        return getVariableValue(SwampScript.SCRIPT_RESULT);
    }
    
}
