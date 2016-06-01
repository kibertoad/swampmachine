package net.kiberion.swampmachine.entities.common.api.resources;

import org.apache.commons.lang3.mutable.MutableLong;

import net.kiberion.swampmachine.entities.common.impl.resources.ResourceDescriptor;

public interface Resource {

    public ResourceDescriptor getDescriptor();
    public MutableLong getValue();
    
}
