package net.kiberion.swampmachine.entities.common.api;

/**
 * @author kibertoad
 * Generic model entity 
 */
public interface ModelEntityDescriptor {

    public String getId();
    public void setId(String toCode);

    public String getGroup();
    public void setGroup(String group);
	
    public String getName();
    public String getExtendedName();
}
