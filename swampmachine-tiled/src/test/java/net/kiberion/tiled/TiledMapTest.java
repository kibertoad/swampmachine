package net.kiberion.tiled;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.spring.ContextBasedTest;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.model.TiledMapPathfindingInfo;
import net.kiberion.tiled.spring.TiledTestConfiguration;

@ContextConfiguration(classes = TiledTestConfiguration.class)
public class TiledMapTest extends ContextBasedTest{
    
    @Autowired
    private MapLoader mapLoader;
    
    @Autowired
    private MapRegistry mapRegistry;
    
    @Before
    public void setup () {
        Assert.assertNotNull(mapLoader);
        
        loadAssets();
        Assert.assertEquals (1, mapRegistry.getRegisteredMaps().size());
    }
    
    @Test
    public void testLoadTiledMap () {
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
    
    @Test
    public void testPathfinding () {
        TiledMap map = mapRegistry.getRegisteredMaps().get("testMap");
        Assert.assertNotNull (map);
        TiledMapInfo info = new TiledMapInfo (map);
        
        TiledMapPathfindingInfo pathfindingInfo = info.getPathfindingInfo();
        assertNotNull (pathfindingInfo);
    }
    
}
