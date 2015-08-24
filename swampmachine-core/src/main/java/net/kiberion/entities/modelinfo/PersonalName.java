package net.kiberion.entities.modelinfo;

import net.kiberion.entities.common.impl.DataNode;

/**
 * @author kibertoad
 */
public class PersonalName extends DataNode {

    public int nationID = -1;
    public String name;

    public PersonalName() {
    }

    public boolean isFirstName() {
        return hasTag("isFirstName");
    }
    public boolean isFamilyName() {
        return hasTag("isFamilyName");
    }
    public boolean isMaleName() {
        return hasTag("isMaleName");
    }
    public boolean isFemaleName() {
        return hasTag("isFemaleName");
    }

}
