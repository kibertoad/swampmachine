package net.kiberion.tiled.model;

import org.apache.commons.lang3.Validate;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class TiledMapInfo {

    private final TiledMap map;
    
    private final int tileWidth;
    private final int tileHeight;

    private final int tileHalfWidth;
    private final int tileHalfHeight;
    
    private final int mapWidth; //in tiles
    private final int mapHeight; //in tiles

    private TiledMapWallInfo wallInfo;
    private TiledMapPathfindingInfo pathfindingInfo;
    

    public TiledMapInfo(TiledMap map) {
        Validate.notNull(map, "Map is null.");
        tileWidth = map.getProperties().get("tilewidth", Integer.class);
        tileHeight = map.getProperties().get("tileheight", Integer.class);
        tileHalfWidth = tileWidth / 2;
        tileHalfHeight = tileHeight / 2;
        
        mapWidth = map.getProperties().get("width", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class);
        this.map = map;
        
        wallInfo = new TiledMapWallInfo(this);
        wallInfo.loadWalls(map);
        pathfindingInfo = new TiledMapPathfindingInfo(this);
    }

    public int getTileHalfWidth() {
        return tileHalfWidth;
    }

    public int getTileHalfHeight() {
        return tileHalfHeight;
    }

    public TiledMap getMap() {
        return map;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
        
    
    public TiledMapWallInfo getWallInfo() {
        return wallInfo;
    }

    public TiledMapPathfindingInfo getPathfindingInfo() {
        return pathfindingInfo;
    }

    
    
}
