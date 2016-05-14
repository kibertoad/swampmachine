package net.kiberion.tiled.managers;

import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.tiled.TiledIsometricCoordsUtils;

public class IsometricMapObjectManager extends MapObjectManager{

    @Override
    public Position getPositionForModelEntity (MetadataHolderBlock entityModel, Position position) {
        return TiledIsometricCoordsUtils.getIsometricScreenCoords(position, getMapInfo());
    }

}
