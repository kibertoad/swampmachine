package net.kiberion.tiled.aspects.holders;

import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.entityblocks.impl.CommonMetadataHolderAspect;

public class CommonMapMetadataHolderAspect extends CommonMetadataHolderAspect implements MapMetadataHolderAspect{
    
    private final CommonPosition position = new CommonPosition(0, 0);

    @Override
    public CommonPosition getPositionAspect() {
        return position;
    }
    

}
