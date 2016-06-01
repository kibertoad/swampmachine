package net.kiberion.swampmachine.entities.common.impl.resources;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableLong;


public class ResourcesDelta {

    private final Map<String, MutableLong> backingMap;
    private final Set<String> supportedResources;
    
    public ResourcesDelta(Set<String> supportedResources) {
        this.supportedResources = supportedResources;
        backingMap = new LinkedHashMap<> (supportedResources.size());
    }

    public MutableLong add(String key, long value) {
        Validate.isTrue(supportedResources.contains(key));
        MutableLong currentValue = backingMap.get(key);
        if (currentValue == null) {
            currentValue = new MutableLong (value);
            return backingMap.put(key, currentValue);
        } else {
            currentValue.add(value);
            return currentValue;
        }
    }
    
    public MutableLong add(String key, MutableLong value) {
        return add (key, value.longValue());
    }
    
    public Set<java.util.Map.Entry<String, MutableLong>> entrySet() {
        return backingMap.entrySet();
    }
    
    public MutableLong getMutableValue (String key) {
        return backingMap.get (key);
    }

}
