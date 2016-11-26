package net.kiberion.swampmachine.api.templating;

import java.util.Map;

public interface Template {

    /**
     * Returns result of template evaluated against a provided map of variables
     * 
     * @param variableMap
     * @return
     */
    public String eval (Map<String, Object> variableMap);
    
}
