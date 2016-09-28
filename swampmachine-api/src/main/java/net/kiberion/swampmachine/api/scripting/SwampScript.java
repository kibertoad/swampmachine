package net.kiberion.swampmachine.api.scripting;

import net.kiberion.swampmachine.api.invokables.ParametrizedInvokableWithResult;

public interface SwampScript extends ParametrizedInvokableWithResult<SwampBinding, SwampScriptInvokationResult>{

    public static final String SCRIPT_RESULT = "scriptResult";
    
}
