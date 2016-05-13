package net.kiberion.swampmachine.aspects.api;

import net.kiberion.swampmachine.entities.common.api.ModelEntityDescriptor;
import net.kiberion.swampmachine.entities.common.api.Taggable;

public interface MetadataAspect extends ModelEntityDescriptor, Taggable{
    
    public int getUID();
    public void setUID(int UID);
    public void setName (String name);
    
    public String getDescription();
    public void setDescription (String description);
    

}
