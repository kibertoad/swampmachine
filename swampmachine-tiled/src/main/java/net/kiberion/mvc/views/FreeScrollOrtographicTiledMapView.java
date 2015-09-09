package net.kiberion.mvc.views;

import net.kiberion.mvc.model.AbstractTiledMapModel;

public class FreeScrollOrtographicTiledMapView<T extends AbstractTiledMapModel<?>> extends OrtographicTiledMapView<T> {

    @Override
    protected void setRendererCameraToCenter() {
    }
    
    @Override
    public void centerCameraOnPlayer() {
    }

}
