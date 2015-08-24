package net.kiberion.mvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.google.inject.Inject;

import net.kiberion.entities.map.api.Position;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.aspects.holders.MapMetadataHolderAspect;
import net.kiberion.tiled.factories.api.MapCreatureFactory;
import net.kiberion.tiled.model.TiledMapInfo;


public class AbstractTiledMapModel<T extends CollidableAspect>{

	protected static final Logger log = LogManager.getLogger();
	
    @Inject
    protected MapCreatureFactory<T> creatureFactory;

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
    

    protected void placeCreatures () {}

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
