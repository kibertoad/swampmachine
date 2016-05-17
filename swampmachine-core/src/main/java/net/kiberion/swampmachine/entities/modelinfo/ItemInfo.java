package net.kiberion.swampmachine.entities.modelinfo;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.groovy.GroovyScript;



/**
 * @author: kibertoad
 */
public class ItemInfo extends CommonModelEntityDescriptor {

    @Setter
    @Getter
    private GroovyScript invokeEffectScript; //effect on being invoked, e. g. eaten

    @Setter
    @Getter
    private GroovyScript pickupEffectScript;

    @Setter
    @Getter
    private boolean consumedOnUse;

    @Setter
    @Getter
    private boolean stackable = true;



}
