package net.kiberion.swampmachine.scripting;

import net.kiberion.entities.common.api.ParametrizedInvokableWithResult;

public interface SwampScript<T extends SwampBinding, I extends SwampScriptInvokationResult> extends ParametrizedInvokableWithResult<T, I>{

}
