package net.kiberion.swampmachine.entityblocks.api;

import java.util.Collection;

public interface MultipleEntityProvider extends EntityProvider<MetadataHolderBlock>{

    public <T extends MetadataHolderBlock> Collection<T> getAllEntities (Class<T> requestedClass);
    public <T extends MetadataHolderBlock> T getEntity (Class<T> requestedClass, String id);
    
    
}
