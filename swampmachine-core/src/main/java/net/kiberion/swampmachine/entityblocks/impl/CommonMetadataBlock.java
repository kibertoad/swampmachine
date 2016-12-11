package net.kiberion.swampmachine.entityblocks.impl;

import java.util.HashSet;
import java.util.Set;

import net.kiberion.swampmachine.entityblocks.api.EntityInstanceMetadataBlock;


public class CommonMetadataBlock implements EntityInstanceMetadataBlock {

    public String containerID;
    public int UID = -1;

    private String id;
    private String groupId;
    private String name; // used for display to user
    
    private Set<String> tags = new HashSet<>();

    @Override
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }
    
    /*
     * E. g. for equipment slot return both slot and item name
     */
    @Override
    public String getExtendedName() {
        return id;
    }

    @Override
    public String getName() {
        if (name != null) {
            return name;
        } else {
            return id;
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getGroup() {
        return this.groupId;
    }

    @Override
    public void setGroup(String toGroup) {
        this.groupId = toGroup;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getUID() {
        return UID;
    }

    @Override
    public void setUID(int UID) {
        this.UID = UID;
    }

    @Override
    public String toString() {
        if (getName() != null) {
            return getName();
        } else {
            return getId();
        }
    }

}
