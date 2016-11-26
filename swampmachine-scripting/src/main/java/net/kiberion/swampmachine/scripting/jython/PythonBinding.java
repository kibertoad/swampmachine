package net.kiberion.swampmachine.scripting.jython;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.kiberion.swampmachine.api.scripting.SwampBinding;

public class PythonBinding extends HashMap<String, Object> implements SwampBinding{

    /**
     * 
     */
    private static final long serialVersionUID = -4611782909570888304L;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getVariableValue(String name) {
        return (T) get(name);
    }

    @Override
    public void setVariable(String name, Object value) {
        put(name, value);
    }
    
    @Override
    public Collection<java.util.Map.Entry<String, Object>> getVariableEntries() {
        return entrySet();
    }
    
    @Override
    public Map<String, Object> getVariableMap() {
        return this;
    }

    @Override
    public boolean hasVariable(String name) {
        return containsKey(name);
    }

}
