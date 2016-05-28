package net.kiberion.swampmachine.groovy;

import java.util.Map;

import org.apache.commons.lang3.Validate;

public class GroovyPriceScript extends GroovyScript{

    private static final String RESULT_VAR = "result";
    
    @SuppressWarnings("unchecked")
    public Map<String, Integer> evaluate (GroovyBinding binding) {
        invoke(binding);
        Map<String, Integer> result = (Map<String, Integer>) binding.getVariable(RESULT_VAR);
        Validate.notNull(result, "Result was not set for this script");
        return result;
    }
    
}
