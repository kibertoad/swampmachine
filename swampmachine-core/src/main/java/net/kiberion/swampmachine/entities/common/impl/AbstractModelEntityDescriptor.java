package net.kiberion.swampmachine.entities.common.impl;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.entityblocks.impl.CommonMetadataHolderBlock;


/**
 * @author kibertoad
 */
public abstract class AbstractModelEntityDescriptor extends CommonMetadataHolderBlock implements EntityModelDescriptor {

    @Getter
    @Setter
    private String description; 

    /*
     * E. g. for equipment slot return both slot and item name
     */
    @Override
    public String getExtendedName() {
        return this.getMetadata().getId();
    }

    @Override
    public String getName() {
        return this.getMetadata().getName();
    }
 
    @Override
    public void setName(String name) {
        this.getMetadata().setName(name);
    }

    @Override
    public String getGroup() {
        return this.getMetadata().getGroup();
    }

    @Override
    public void setGroup(String toGroup) {
    	this.getMetadata().setGroup(toGroup);
    }

    @Override
    public String getId() {
        return getMetadata().getId();
    }

    @Override
    public void setId(String id) {
        getMetadata().setId(id);
    }

    @Override
    public String toString() {
        return this.getMetadata().toString();
    }
    
}
