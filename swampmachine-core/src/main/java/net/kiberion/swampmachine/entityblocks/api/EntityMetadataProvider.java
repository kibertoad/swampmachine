package net.kiberion.swampmachine.entityblocks.api;

import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;

public interface EntityMetadataProvider<T extends EntityModelDescriptor> {

    public T getMetadataForEntity (String entityId);
    
}
