package net.kiberion.swampmachine.entityblocks.api;

import java.util.Collection;

public interface EntityProvider<T extends MetadataHolderBlock> {

    public Collection<T> getAllEntities ();
    public T getEntity (String id);
    
}
