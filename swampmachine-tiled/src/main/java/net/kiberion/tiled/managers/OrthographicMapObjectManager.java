package net.kiberion.tiled.managers;

import net.kiberion.swampmachine.aspects.api.MetadataHolderAspect;
import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.tiled.TiledOrthographicCoordsUtils;

public class OrthographicMapObjectManager extends MapObjectManager{

    @Override
    public PositionAspect getPositionForModelEntity (MetadataHolderAspect entityModel, PositionAspect position) {
        return TiledOrthographicCoordsUtils.getOrthographicScreenCoords(position, getMapInfo());
    }

}
