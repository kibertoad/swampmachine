package net.kiberion.tiled.overlays;

import com.badlogic.gdx.graphics.OrthographicCamera;

import net.kiberion.tiled.model.TiledMapInfo;

/**
 * An overlay to be drawn on top of a tile map.
 * @author Cameron Seebach
 */
public interface TiledMapOverlay {

    public void draw(OrthographicCamera camera);
    public void setMap (TiledMapInfo mapInfo);
    public boolean isEnabled ();
    
    public void enable ();
    public void disable ();

}
