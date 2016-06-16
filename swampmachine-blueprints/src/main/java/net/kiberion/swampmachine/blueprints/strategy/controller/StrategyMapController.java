package net.kiberion.swampmachine.blueprints.strategy.controller;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.tiled.entityblocks.holders.MapMetadataHolderBlock;
import net.kiberion.tiled.model.TiledMapInfo;

public class StrategyMapController<TModel extends AbstractTiledMapModel<? extends MapMetadataHolderBlock>>{

    private TiledMapInfo mapInfo;
    
    public void setMap(TiledMapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }
    
}
