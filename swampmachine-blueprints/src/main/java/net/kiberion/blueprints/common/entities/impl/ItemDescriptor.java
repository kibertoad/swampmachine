package net.kiberion.blueprints.common.entities.impl;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.scripting.SwampScript;



/**
 * @author: kibertoad
 */
public class ItemDescriptor extends CommonModelEntityDescriptor {

    @Setter
    @Getter
    private SwampScript invokeEffectScript; //effect on being invoked, e. g. eaten

    @Setter
    @Getter
    private SwampScript pickupEffectScript;

    @Setter
    @Getter
    private boolean consumedOnUse;

    @Setter
    @Getter
    private boolean stackable = true;



}
