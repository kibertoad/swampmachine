package pyramide9.map2.managers;

import net.kiberion.entities.map.api.Position;
import pyramide9.map2.TiledOrthographicCoordsUtils;
import pyramide9.map2.aspects.holders.MetadataHolderAspect;

public class OrthographicMapObjectManager extends MapObjectManager{

    @Override
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, Position position) {
        return TiledOrthographicCoordsUtils.getOrthographicScreenCoords(position, getMapInfo());
    }

}
