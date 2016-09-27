package net.kiberion.swampmachine.scripting;

import net.kiberion.entities.common.api.ParametrizedInvokableWithResult;

public interface SwampScript extends ParametrizedInvokableWithResult<SwampBinding, SwampScriptInvokationResult>{

    public static final String SCRIPT_RESULT = "scriptResult";
    
}
