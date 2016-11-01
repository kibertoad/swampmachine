package net.kiberion.swampmachine.blueprints.common.entities.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.blueprints.common.entities.api.Trait;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

public class CommonTrait extends CommonModelEntityDescriptor implements Trait{

    @Getter
    @Setter
    //This trait cannot be taken while having any traits with given tags
    private List<String> exclusiveWithTag;
    
}
