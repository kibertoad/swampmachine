package net.kiberion.tiled.managers;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.entities.map.api.Position;
import net.kiberion.tiled.TiledOrthographicCoordsUtils;

public class OrthographicMapObjectManager extends MapObjectManager{

    @Override
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, Position position) {
        return TiledOrthographicCoordsUtils.getOrthographicScreenCoords(position, getMapInfo());
    }

}
