package net.kiberion.entities.common.impl;

import java.util.List;

import net.kiberion.aspects.impl.GenericMetadataHolderAspect;
import net.kiberion.entities.common.api.NodeInterface;
import net.kiberion.entities.common.api.TaggedEntity;


/**
 * @author kibertoad
 */
public class EntityModel extends GenericMetadataHolderAspect implements NodeInterface, TaggedEntity {

    @Override
    public int getCustomValue(String valueCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasTag(String theTag) {
        throw new UnsupportedOperationException();
    }

    /*
     * E. g. for equipment slot return both slot and item name
     */
    @Override
    public String getExtendedName() {
        return this.getMetadata().getId();
    }

    @Override
    public String getName() {
        return this.getMetadata().getId();
    }

    public void setName(String name) {
        this.getMetadata().setName(name);
    }

    @Override
    public String getGroup() {
        return "-";
    }

    @Override
    public void setGroup(String toGroup) {

    }

    public void setSubGroup(String toGroup) {

    }

    @Override
    public void outputTags() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getTags() {
        return null;
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
    public int getContainerID() {
        return getMetadata().getContainerID();
    }

    @Override
    public int getRating() {
        return -1;
    }

    @Override
    public void setContainerID(int containerID) {
        this.getMetadata().setContainerID(containerID);
    }
    @Override
    public String toString() {
        return this.getMetadata().toString();
    }
}
