package net.kiberion.swampmachine.entityblocks.api;


public interface MetadataHolderBlock extends IdHolderBlock, NameHolderBlock{

    public EntityInstanceMetadataBlock getMetadata();
    public void setMetadata(EntityInstanceMetadataBlock metadata);
     
    @Override
    public default String getId () {
        return getMetadata().getId();
    }
    
    @Override
    default String getName() {
        return getMetadata().getName();
    }

}
