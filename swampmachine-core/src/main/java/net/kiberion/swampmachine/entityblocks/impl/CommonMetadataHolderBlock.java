package net.kiberion.swampmachine.entityblocks.impl;

import net.kiberion.swampmachine.entityblocks.api.EntityInstanceMetadataBlock;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;

public class CommonMetadataHolderBlock implements MetadataHolderBlock{

    private EntityInstanceMetadataBlock metadata;

    @Override
    public EntityInstanceMetadataBlock getMetadata() {
        return metadata;
    }

    public CommonMetadataHolderBlock() {
        metadata = new CommonMetadataBlock();
    }

    @Override
    public void setMetadata(EntityInstanceMetadataBlock metadata) {
        this.metadata = metadata; 
    }
    
    @Override
    public String toString() {
        return getMetadata().getName();
    }
    
}
