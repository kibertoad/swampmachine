package net.kiberion.swampmachine.groovy;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import groovy.lang.Binding;
import net.kiberion.swampmachine.scripting.SwampBinding;

@SuppressWarnings("unchecked")
public class GroovyBinding extends Binding implements SwampBinding{

    @Override
    public Collection<Entry<String, Object>> getVariableEntries() {
        return this.getVariables().entrySet();
    }
    
    @Override
    public Map<String, Object> getVariableMap() {
        return this.getVariables();
    }

}
