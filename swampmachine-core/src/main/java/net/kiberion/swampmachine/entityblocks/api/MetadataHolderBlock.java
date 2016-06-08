package net.kiberion.swampmachine.entityblocks.api;


public interface MetadataHolderBlock extends IdHolderBlock{

    public EntityInstanceMetadataBlock getMetadata();
    public void setMetadata(EntityInstanceMetadataBlock metadata);
     
    @Override
    public default String getId () {
        return getMetadata().getId();
    }

}
