package net.kiberion.swampmachine.aspects.impl;

import net.kiberion.swampmachine.aspects.api.MetadataAspect;
import net.kiberion.swampmachine.aspects.api.MetadataHolderAspect;

public class CommonMetadataHolderAspect implements MetadataHolderAspect{

    private MetadataAspect metadata;

    @Override
    public MetadataAspect getMetadata() {
        return metadata;
    }

    public CommonMetadataHolderAspect() {
        metadata = new CommonMetadataAspect();
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
