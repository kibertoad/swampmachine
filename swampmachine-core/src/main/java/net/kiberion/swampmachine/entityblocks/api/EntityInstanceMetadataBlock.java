package net.kiberion.swampmachine.entityblocks.api;

import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.entities.common.api.Taggable;

public interface EntityInstanceMetadataBlock extends EntityModelDescriptor, Taggable{
    
    /**
     * 
     * @return int Unique identifier of an entity in the universe
     */
    public int getUID();
    public void setUID(int UID);
    
    public void setName (String name);
    
    public String getDescription();
    public void setDescription (String description);
    

}
