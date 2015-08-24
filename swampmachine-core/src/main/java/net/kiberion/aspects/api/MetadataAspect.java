package net.kiberion.aspects.api;

import net.kiberion.entities.common.api.NodeEntity;
import net.kiberion.entities.common.api.TaggedEntity;

public interface MetadataAspect extends NodeEntity, TaggedEntity{
    
    public int getUID();
    public void setUID(int UID);
    public void setName (String name);
    
    public String getDescription();
    public void setDescription (String description);
    

}
