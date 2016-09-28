package net.kiberion.swampmachine.api.templating;

import java.util.Map;

public interface Template {

    
    public String eval (Map<String, Object> variableMap);
    
    
}
