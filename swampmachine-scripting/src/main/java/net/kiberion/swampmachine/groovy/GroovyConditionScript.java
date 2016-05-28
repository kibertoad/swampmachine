package net.kiberion.swampmachine.groovy;

import org.apache.commons.lang3.Validate;

public class GroovyConditionScript extends GroovyScript{

    private static final String RESULT_VAR = "result";
    
    public boolean evaluate (GroovyBinding binding) {
        invoke(binding);
        Boolean result = (Boolean) binding.getVariable(RESULT_VAR);
        Validate.notNull(result, "Result was not set for this script");
        return result;
    }
    
}
