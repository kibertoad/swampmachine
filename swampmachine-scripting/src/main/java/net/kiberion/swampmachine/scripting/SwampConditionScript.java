package net.kiberion.swampmachine.scripting;

import org.apache.commons.lang3.Validate;

import lombok.Getter;

public class SwampConditionScript {

    @Getter
    private final SwampScript script;
    
    private static final String RESULT_VAR = "result";
    
    
    public SwampConditionScript(SwampScript script) {
        this.script = script;
    }

    public boolean evaluate (SwampBinding binding) {
        getScript().invoke(binding);
        Boolean result = binding.getVariable(RESULT_VAR);
        Validate.notNull(result, "Result was not set for this script");
        return result;
    }

   
}
