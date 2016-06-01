package net.kiberion.swampmachine.entities.common.impl.resources;

import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableLong;


public class ResourcesDelta extends LinkedHashMap<String, MutableLong>{

    private final Set<String> supportedResources;
    private static final long serialVersionUID = 7546187766869224224L;
    
    public ResourcesDelta(Set<String> supportedResources) {
        this.supportedResources = supportedResources;
    }

    public MutableLong put(String key, long value) {
        Validate.isTrue(supportedResources.contains(key));
        MutableLong currentValue = get(key);
        if (currentValue == null) {
            currentValue = new MutableLong (value);
            return super.put(key, currentValue);
        } else {
            currentValue.add(value);
            return currentValue;
        }
    }
    
    @Override
    public MutableLong put(String key, MutableLong value) {
        return put (key, value.longValue());
    }

}
