package net.kiberion.entities.common.api;

/**
 * @author kibertoad
 */
public interface NodeEntity {

    public String getId();
    public void setId(String toCode);

    public String getGroup();
    public void setGroup(String group);
	
    public String getName();
    public String getExtendedName();
}
