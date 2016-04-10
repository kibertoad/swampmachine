package net.kiberion.mvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.entities.map.api.Position;
import net.kiberion.mvc.model.helpers.SpawningHelper;
import net.kiberion.tiled.aspects.holders.MapMetadataHolderAspect;
import net.kiberion.tiled.model.TiledMapInfo;


public abstract class AbstractTiledMapModel<T extends MapMetadataHolderAspect>{

	protected static final Logger log = LogManager.getLogger();
	
	@Autowired
	protected SpawningHelper spawningHelper;
	
    private TiledMap map;
    private TiledMapInfo mapInfo;
    
    private T player;
    private final List<T> creatures = new ArrayList<>();
    private final List<T> aiCreatures = new ArrayList<>();

    private final List<MapMetadataHolderAspect> activeObjects = new ArrayList<>();
    private final Map<Position, MapMetadataHolderAspect> activeObjectsMap = new HashMap<> ();
    
    public void spawnCreatures () {};

    public List<T> getCreatures() {
        return creatures;
    }
    
    public List<T> getAiCreatures() {
        return aiCreatures;
    }
    

    protected abstract void placeCreatures ();

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
        this.mapInfo = new TiledMapInfo (map);
    }    

    public TiledMapInfo getMapInfo() {
        return mapInfo;
    }

    public T getPlayer() {
        return player;
    }

    public void setPlayer(T player) {
        this.player = player;
    }

    public List<MapMetadataHolderAspect> getActiveObjects() {
        return activeObjects;
    }

    public Map<Position, MapMetadataHolderAspect> getActiveObjectsMap() {
        return activeObjectsMap;
    }

}
