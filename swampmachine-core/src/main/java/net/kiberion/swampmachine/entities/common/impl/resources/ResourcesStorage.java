package net.kiberion.swampmachine.entities.common.impl.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableLong;

public class ResourcesStorage {

    private final Map<String, MutableLong> backingMap;

    public ResourcesStorage(Collection<String> supportedResources) {
        backingMap = new HashMap<>(supportedResources.size());
        for (String resourceId : supportedResources) {
            backingMap.put(resourceId, new MutableLong());
        }
    }

    public void applyDelta(ResourcesDelta delta) {
        Validate.notNull(delta);

        for (java.util.Map.Entry<String, MutableLong> entry : delta.entrySet()) {
            MutableLong resource = backingMap.get(entry.getKey());
            resource.add(entry.getValue().longValue());
        }
    }

    public boolean canAfford(ResourcesDelta delta) {
        for (java.util.Map.Entry<String, MutableLong> entry : delta.entrySet()) {
            if (backingMap.get(entry.getKey()).getValue().longValue() < entry.getValue().longValue()) {
                return false;
            }
        }
        return true;
    }

    public long getValue(String resourceId) {
        return backingMap.get(resourceId).longValue();
    }

}