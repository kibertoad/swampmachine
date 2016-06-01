package net.kiberion.swampmachine.entities.common.impl.resources;

import java.util.HashMap;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableLong;

import net.kiberion.swampmachine.entities.common.api.resources.Resource;

public class ResourcesStorage extends HashMap<String, Resource> {

    private static final long serialVersionUID = -7620447256916861506L;

    public void applyDelta(ResourcesDelta delta) {
        Validate.notNull(delta);

        for (java.util.Map.Entry<String, MutableLong> entry : delta.entrySet()) {
            Resource resource = get (entry.getKey());
            resource.getValue().add(entry.getValue().longValue());
        }
        
    }

}
