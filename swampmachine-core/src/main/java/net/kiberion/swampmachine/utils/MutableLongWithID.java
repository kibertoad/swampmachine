package net.kiberion.swampmachine.utils;

import org.apache.commons.lang3.mutable.MutableLong;

import net.kiberion.swampmachine.entityblocks.api.EntityInstanceMetadataBlock;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.entityblocks.api.NameHolderBlock;

public class MutableLongWithID extends MutableLong implements MetadataHolderBlock, NameHolderBlock{

    private static final long serialVersionUID = -7484554133137902498L;
    private final String id;
    
    public MutableLongWithID(String id) {
        this.id = id;
    }
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public EntityInstanceMetadataBlock getMetadata() {
        return null;
    }

    @Override
    public void setMetadata(EntityInstanceMetadataBlock metadata) {
    }

}
