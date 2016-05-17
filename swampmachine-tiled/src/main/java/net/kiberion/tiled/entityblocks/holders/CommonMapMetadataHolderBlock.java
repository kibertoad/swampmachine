package net.kiberion.tiled.entityblocks.holders;

import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.entityblocks.impl.CommonMetadataHolderBlock;

public class CommonMapMetadataHolderBlock extends CommonMetadataHolderBlock implements MapMetadataHolderBlock{
    
    private final CommonPosition position = new CommonPosition(0, 0);

    @Override
    public CommonPosition getPositionAspect() {
        return position;
    }
    

}
