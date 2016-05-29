package net.kiberion.swampmachine.scripting;

import java.util.Map;

import org.apache.commons.lang3.Validate;

import lombok.Getter;

public class SwampPriceScript{

    @Getter
    private final SwampScript script;
    
    private static final String RESULT_VAR = "result";

    
    public SwampPriceScript(SwampScript script) {
        this.script = script;
    }
    
    public Map<String, Integer> evaluate (SwampBinding binding) {
        getScript().invoke(binding);
        Map<String, Integer> result = binding.getVariableValue(RESULT_VAR);
        Validate.notNull(result, "Result was not set for this script");
        return result;
    }

}
