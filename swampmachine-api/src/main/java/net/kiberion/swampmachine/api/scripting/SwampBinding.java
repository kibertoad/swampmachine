package net.kiberion.swampmachine.api.scripting;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public interface SwampBinding {

    public <T> T getVariableValue(String name);
    public boolean hasVariable(String name);
    public void setVariable(String name, Object value);
    public Collection<Entry<String, Object>> getVariableEntries();
    public Map<String, Object> getVariableMap();
    
}
