package net.kiberion.mvc.views;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.tiled.TiledOrthographicCoordsUtils;
import net.kiberion.tiled.renderers.OrthogonalTiledMapRendererWithObjects;

public class OrtographicTiledMapView extends AbstractTiledMapView{

    @Override
    protected void instantiateRenderer() {
        renderer = new OrthogonalTiledMapRendererWithObjects(getMap());
    }

    @Override
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, PositionAspect position) {
        return TiledOrthographicCoordsUtils.getOrthographicScreenCoords(position, getMapInfo());
    }    
	
}
