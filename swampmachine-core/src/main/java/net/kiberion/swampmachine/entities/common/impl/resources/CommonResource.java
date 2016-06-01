package net.kiberion.swampmachine.entities.common.impl.resources;

import org.apache.commons.lang3.mutable.MutableLong;

import net.kiberion.swampmachine.entities.common.api.resources.Resource;
import net.kiberion.swampmachine.entityblocks.impl.CommonMetadataHolderBlock;

public class CommonResource extends CommonMetadataHolderBlock implements Resource{

    private final ResourceDescriptor descriptor;
    private final MutableLong value = new MutableLong();
    
    
    public CommonResource(ResourceDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public ResourceDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public MutableLong getValue() {
        return value;
    }

}
