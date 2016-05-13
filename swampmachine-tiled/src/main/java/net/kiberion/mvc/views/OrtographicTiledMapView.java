package net.kiberion.mvc.views;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.swampmachine.aspects.api.MetadataHolderAspect;
import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.tiled.TiledOrthographicCoordsUtils;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.renderers.OrthogonalTiledMapRendererWithObjects;

public class OrtographicTiledMapView<T extends AbstractTiledMapModel<?>> extends AbstractTiledMapView<T>{

    @Override
    protected void instantiateRenderer() {
        //Validate.notNull(getCamera());
        renderer = new OrthogonalTiledMapRendererWithObjects(getMap());
        super.instantiateRenderer();
    }

    @Override
    public PositionAspect getPositionForModelEntity (MetadataHolderAspect entityModel, CommonPosition position) {
        return TiledOrthographicCoordsUtils.getOrthographicScreenCoords(position, getMapInfo());
    }    
    
    @Override
    public void centerCameraOnPlayer() {
        getCamera().centerOn(getModel().getPlayer().getPositionAspect());
    } 
    
    protected void setRendererCameraToCenter () {
        renderer.setPositionToCenter(getModel().getPlayer().getPositionAspect());
    }
    
    @Override
    public void setMap(TiledMapInfo mapInfo) {
        super.setMap(mapInfo);

        renderer.setOrthoCam(getCamera().getOrthoCam());;
        setRendererCameraToCenter();
    }

	
}
