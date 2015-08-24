package net.kiberion.mvc.views;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.tiled.TiledOrthographicCoordsUtils;
import net.kiberion.tiled.renderers.OrthogonalTiledMapRendererWithObjects;

public class OrtographicTiledMapView<T extends AbstractTiledMapModel<?>> extends AbstractTiledMapView<T>{

    @Override
    protected void instantiateRenderer() {
        renderer = new OrthogonalTiledMapRendererWithObjects(getMap());
        super.instantiateRenderer();
    }

    @Override
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, PositionAspect position) {
        return TiledOrthographicCoordsUtils.getOrthographicScreenCoords(position, getMapInfo());
    }    
    
    @Override
    public void centerCameraOnPlayer() {
        getCamera().centerOn2(getModel().getPlayer().getPositionAspect());
    } 

	
}
