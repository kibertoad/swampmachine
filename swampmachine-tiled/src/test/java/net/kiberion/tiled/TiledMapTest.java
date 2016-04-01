package net.kiberion.tiled;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import net.kiberion.spring.ContextBasedTest;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.spring.TestConfigurationTiled;

@ContextConfiguration(classes = TestConfigurationTiled.class)
public class TiledMapTest extends ContextBasedTest{

    private static final String pathToTileMap = "test_map.tmx";
    
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
        //LoaderScanner scanner = new LoaderScanner();
       // scanner.scan(null);
    }
    
}
