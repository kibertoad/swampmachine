package net.kiberion.swampmachine.entityblocks.impl;

import java.util.Set;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesDelta;
import net.kiberion.swampmachine.entityblocks.api.ResourceCostBlock;

public class CommonResourceCostBlock implements ResourceCostBlock{

    @Getter
    private ResourcesDelta cost;
    
    
    public CommonResourceCostBlock(Set<String> supportedResources) {
        cost = new ResourcesDelta(supportedResources);
    }

    
}
