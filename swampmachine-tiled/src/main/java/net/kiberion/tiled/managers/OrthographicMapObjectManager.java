package net.kiberion.tiled.managers;

import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.tiled.TiledOrthographicCoordsUtils;

public class OrthographicMapObjectManager extends MapObjectManager{

    @Override
    public Position getPositionForModelEntity (MetadataHolderBlock entityModel, Position position) {
        return TiledOrthographicCoordsUtils.getOrthographicScreenCoords(position, getMapInfo());
    }

}
