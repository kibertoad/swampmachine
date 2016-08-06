package net.kiberion.tiled;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.model.TiledMapPathfindingInfo;


public class TiledMapTest extends MapContextBasedTest{
    
    @Autowired
    private MapLoader mapLoader;
    
    @Autowired
    private MapRegistry mapRegistry;
    
    //Shouldn't be @Before statement, because otherwise assets will fail due to map already being loaded before the context will reload
    public void setup () {
        Assert.assertEquals (0, mapRegistry.getRegisteredMaps().size());
        loadAssets();
        Assert.assertNotNull(mapLoader);
        Assert.assertEquals (1, mapRegistry.getRegisteredMaps().size());
    }

    @DirtiesContext
    @Test
    public void testLoadTiledMap () {
        setup();
        TiledMap map = mapRegistry.getRegisteredMaps().get("testMap");
        Assert.assertNotNull (map);

        TiledMapInfo info = new TiledMapInfo (map);
        assertEquals (102, info.getMapHeight());
        assertEquals (105, info.getMapWidth());
        assertEquals (64, info.getTileWidth());
        assertEquals (64, info.getTileHeight());
        assertEquals (32, info.getTileHalfHeight());
        assertEquals (32, info.getTileHalfWidth());
        assertEquals (map, info.getMap());
    }
    
    @DirtiesContext
    @Test
    public void testPathfinding () {
        setup();
        TiledMap map = mapRegistry.getRegisteredMaps().get("testMap");
        Assert.assertNotNull (map);
        TiledMapInfo info = new TiledMapInfo (map);
        
        TiledMapPathfindingInfo pathfindingInfo = info.getPathfindingInfo();
        assertNotNull (pathfindingInfo);
    }
    
}
