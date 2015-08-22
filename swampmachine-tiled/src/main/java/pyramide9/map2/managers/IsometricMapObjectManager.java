package pyramide9.map2.managers;

import net.kiberion.entities.map.api.Position;
import pyramide9.map2.TiledIsometricCoordsUtils;
import pyramide9.map2.aspects.holders.MetadataHolderAspect;

public class IsometricMapObjectManager extends MapObjectManager{

    @Override
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, Position position) {
        return TiledIsometricCoordsUtils.getIsometricScreenCoords(position, getMapInfo());
    }

}
