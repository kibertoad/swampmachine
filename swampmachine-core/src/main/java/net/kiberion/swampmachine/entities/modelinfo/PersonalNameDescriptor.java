package net.kiberion.swampmachine.entities.modelinfo;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 * @author kibertoad
 */
public class PersonalNameDescriptor extends CommonModelEntityDescriptor {

    public static final String IS_FIRST_NAME = "isFirstName";
    public static final String IS_FAMILY_NAME = "isFamilyName";
    public static final String IS_MALE_NAME = "isMaleName";
    public static final String IS_FEMALE_NAME = "isFemaleName";
    
    @Getter
    @Setter
    private int nationID = -1;
    
    @Getter
    @Setter
    private String name;

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
