/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.tiled.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.tiled.TiledIsometricCoordsUtils;
import net.kiberion.tiled.model.TiledMapInfo;

/**
 * A simplified camera for use in viewing a TiledMapView.
 * @author Cameron Seebach
 */
public class TiledMapCamera {
    
    private final OrthographicCamera orthoCamera;
    private final int tileWidth;
    private final int tileHeight;
    @SuppressWarnings("unused")
    private final int mapWidth;
    private final int mapHeight;

    public TiledMapCamera(TiledMap map) {
        if (map == null) {
            throw new IllegalArgumentException("Map is null.");
        }
        
        tileWidth = map.getProperties().get("tilewidth", Integer.class);
        tileHeight = map.getProperties().get("tileheight", Integer.class);
        mapWidth = map.getProperties().get("width", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class);
        
        orthoCamera = new OrthographicCamera();
        orthoCamera.setToOrtho(false);
        orthoCamera.update();
    }
    
    /**
     * Center the view on the specified object. Object's coordinates must be
     * model coordinates.
     * @param object 
     */
    public void centerOn(Position object){
        float centerX = object.getX() * tileWidth + tileWidth / 2f;
        float yInverted = mapHeight - object.getY() - 1f;
        float centerY = yInverted * tileHeight + tileHeight / 2f;
        orthoCamera.position.set(centerX, centerY, 0);
        orthoCamera.update();
    }
    
    public void centerOn(PositionAspect position) {
        float centerX = position.getX() * tileWidth + tileWidth / 2f;
        float yInverted = position.getY() - 1f;
        float centerY = yInverted * tileHeight + tileHeight / 2f;
        orthoCamera.position.set(centerX, centerY, 0);
        orthoCamera.update();
    }
    
    //to be used in new renderer
    public void centerOn2(PositionAspect positionAspect) {
        orthoCamera.position.set(positionAspect.getX() + (tileWidth / 2), positionAspect.getY() - (tileHeight / 2), 0);
        orthoCamera.update();
    }
    
    //to be used in new isometrical renderer
    public void centerIsometrically(PositionAspect positionAspect, TiledMapInfo mapInfo) {
        Position position = TiledIsometricCoordsUtils.getIsometricScreenCoords(positionAspect, mapInfo);
        orthoCamera.position.set(position.getX(), position.getY(), 0);
        orthoCamera.update();
    }
    
    
    
    
    public void setZoom(float zoom){
        orthoCamera.zoom = 1f/zoom;
        orthoCamera.update();
    }
    
    /**
     * Scroll the camera. Coordinates specified in window pixels. The scroll 
     * amount is adjusted for zoom.
     * @param dx
     * @param dy 
     */
    public void scroll(float dx, float dy){
        orthoCamera.position.add(dx*orthoCamera.zoom, -dy*orthoCamera.zoom, 0);
        orthoCamera.update();
    }
    
    public OrthographicCamera getOrthoCam(){
        return orthoCamera;
    }
    
    public void apply(){
        //orthoCamera.apply(Gdx.graphics.getGL20());
    }
    
    public Position getTileCoords(Position screenCoords){
        return null;
    }

    public void resetCamera() {
        this.orthoCamera.position.x = 0;
        this.orthoCamera.position.y = 0;
        this.orthoCamera.update();
    }

    public Position getPosition() {
        return new PositionAspect (this.orthoCamera.position.x, this.orthoCamera.position.y);
    }


}
