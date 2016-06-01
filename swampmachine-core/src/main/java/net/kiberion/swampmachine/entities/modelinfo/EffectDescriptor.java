package net.kiberion.swampmachine.entities.modelinfo;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.AbstractModelEntityDescriptor;

/**
 * E. g. "Set status to POISONED and inflict 5 points of damage"
 * 
 * @author kibertoad
 */
public class EffectDescriptor extends AbstractModelEntityDescriptor{

    @Getter
    @Setter
    private int value;

    @Getter
    @Setter
    private Map<String, Object> properties;

}
