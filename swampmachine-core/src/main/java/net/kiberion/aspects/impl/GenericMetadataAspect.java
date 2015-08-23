package net.kiberion.aspects.impl;

import java.util.List;

import net.kiberion.aspects.api.MetadataAspect;


public class GenericMetadataAspect implements MetadataAspect {

    public String containerID;
    public int UID = -1;

    private String id;
    private String name; // used for display to user
    private String description;

    @Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

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
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getContainerID() {
        return containerID; // To change body of implemented methods use File |
                            // Settings | File Templates.
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
    public int getRating() {
        throw new RuntimeException("unimpl");
    }

    @Override
    public void setContainerID(String containerID) {
        this.containerID = containerID;
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
