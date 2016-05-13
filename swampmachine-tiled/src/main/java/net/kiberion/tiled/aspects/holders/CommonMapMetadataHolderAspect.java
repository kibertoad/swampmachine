package net.kiberion.tiled.aspects.holders;

import net.kiberion.swampmachine.aspects.impl.CommonMetadataHolderAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;

public class CommonMapMetadataHolderAspect extends CommonMetadataHolderAspect implements MapMetadataHolderAspect{
    
    private final CommonPosition position = new CommonPosition(0, 0);

    @Override
    public CommonPosition getPositionAspect() {
        return position;
    }
    

}
