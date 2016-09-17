package net.kiberion.swampmachine.entities.common.api;

import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;

/**
 * @author kibertoad Generic model entity
 */
public interface EntityModelDescriptor extends IdHolderBlock{

    /**
     * 
     * @return String ID of an entity prototype, e. g. "longsword". Note that
     *         specific instances of that entity will also have their personal
     *         UIDs.
     */
    public String getId();

    public void setId(String toCode);

    /**
     * @return String ID of a group that the entity belongs to, e. g. "weapon"
     */
    public String getGroup();

    public void setGroup(String group);

    /**
     * @return String Ccommon entity name, e. g. "Guard"; specific entity
     *         instance in the universe can also have a personal name
     */
    public String getName();
    
    public void setName (String name);

    public String getExtendedName();

}
