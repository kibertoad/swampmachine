package net.kiberion.tiled.managers;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.entities.map.api.Position;
import net.kiberion.tiled.TiledIsometricCoordsUtils;

public class IsometricMapObjectManager extends MapObjectManager{

    @Override
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, Position position) {
        return TiledIsometricCoordsUtils.getIsometricScreenCoords(position, getMapInfo());
    }

}
