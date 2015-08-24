package net.kiberion.tiled.entities.impl;

import net.kiberion.tiled.aspects.api.ViewAspect;
import net.kiberion.tiled.aspects.holders.CommonMapMetadataHolderAspect;
import net.kiberion.tiled.entities.api.MapCreature;

public class CommonMapCreature extends CommonMapMetadataHolderAspect implements MapCreature{

    private ViewAspect viewAspect;
    
    @Override
    public ViewAspect getViewAspect() {
        return viewAspect;
    }
    
}
