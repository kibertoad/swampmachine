package net.kiberion.blueprints.strategy.controller;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.tiled.aspects.holders.MapMetadataHolderAspect;
import net.kiberion.tiled.model.TiledMapInfo;

public class StrategyMapController<TModel extends AbstractTiledMapModel<? extends MapMetadataHolderAspect>>{

    private TiledMapInfo mapInfo;
    
    public void setMap(TiledMapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }
    
}
