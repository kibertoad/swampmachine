package net.kiberion.entities.common.api;

/**
 * @author kibertoad
 */
public interface TaggedEntity {

    public void setContainerID(String containerID);
    public String getContainerID();

    public String getId();

    public void setId(String toCode);

    public String getGroup();
    public void setGroup(String group);

    public boolean hasTag(String theTag);

}
