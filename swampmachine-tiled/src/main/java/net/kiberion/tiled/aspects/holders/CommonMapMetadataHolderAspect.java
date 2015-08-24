package net.kiberion.tiled.aspects.holders;

import net.kiberion.aspects.impl.CommonMetadataHolderAspect;
import net.kiberion.entities.map.impl.PositionAspect;

public class CommonMapMetadataHolderAspect extends CommonMetadataHolderAspect implements MapMetadataHolderAspect{
    
    private final PositionAspect position = new PositionAspect(0, 0);

    @Override
    public PositionAspect getPositionAspect() {
        return position;
    }
    

}
