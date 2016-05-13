package net.kiberion.swampmachine.entities.modelinfo;

import net.kiberion.swampmachine.entities.common.impl.DataNode;

/**
 * @author kibertoad
 */
public class PersonalName extends DataNode {

    public static final String IS_FIRST_NAME = "isFirstName";
    public static final String IS_FAMILY_NAME = "isFamilyName";
    public static final String IS_MALE_NAME = "isMaleName";
    public static final String IS_FEMALE_NAME = "isFemaleName";
    
    public int nationID = -1;
    public String name;

    public PersonalName() {
    }

    public boolean isFirstName() {
        return hasTag(IS_FIRST_NAME);
    }
    public boolean isFamilyName() {
        return hasTag(IS_FAMILY_NAME);
    }
    public boolean isMaleName() {
        return hasTag(IS_MALE_NAME);
    }
    public boolean isFemaleName() {
        return hasTag(IS_FEMALE_NAME);
    }

}
