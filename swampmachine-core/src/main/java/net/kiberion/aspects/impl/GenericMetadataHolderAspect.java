package net.kiberion.aspects.impl;

import net.kiberion.aspects.api.MetadataAspect;
import net.kiberion.aspects.api.MetadataHolderAspect;

public class GenericMetadataHolderAspect implements MetadataHolderAspect{

    private MetadataAspect metadata;

    @Override
    public MetadataAspect getMetadata() {
        return metadata;
    }

    public GenericMetadataHolderAspect() {
        metadata = new GenericMetadataAspect();
    }

    @Override
    public void setMetadata(MetadataAspect metadata) {
        this.metadata = metadata; 
    }
    
    @Override
    public String toString() {
        return getMetadata().getName();
    }
    
}
