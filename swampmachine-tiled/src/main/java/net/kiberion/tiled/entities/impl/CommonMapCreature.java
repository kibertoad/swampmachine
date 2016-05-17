package net.kiberion.tiled.entities.impl;

import net.kiberion.tiled.entities.api.MapCreature;
import net.kiberion.tiled.entityblocks.api.ViewBlock;
import net.kiberion.tiled.entityblocks.holders.CommonMapMetadataHolderBlock;

public class CommonMapCreature extends CommonMapMetadataHolderBlock implements MapCreature{

    private ViewBlock viewAspect;
    
    @Override
    public ViewBlock getViewAspect() {
        return viewAspect;
    }
    
}
