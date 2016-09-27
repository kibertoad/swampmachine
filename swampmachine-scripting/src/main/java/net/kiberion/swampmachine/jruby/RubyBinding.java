package net.kiberion.swampmachine.jruby;

import java.util.Collection;
import java.util.Map;

import javax.script.SimpleBindings;

import net.kiberion.swampmachine.scripting.SwampBinding;

public class RubyBinding extends SimpleBindings implements SwampBinding{

    public RubyBinding() {
    }
    
    public RubyBinding(Map<String,Object> m) {
        super (m);
    }
    
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
