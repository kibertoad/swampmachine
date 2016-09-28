package net.kiberion.swampmachine.templating.api;

import java.util.Map;

public interface Template {

    
    public String eval (Map<String, Object> variableMap);
    
    
}
