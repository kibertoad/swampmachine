package net.kiberion.common.factory;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.test.context.ContextConfiguration;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.factories.events.SpawnEntityEvent;
import net.kiberion.swampmachine.spring.ContextBasedTest;
import net.kiberion.tiled.MapRegistry;
import net.kiberion.tiled.factories.impl.params.TextureMapObjectSpawnParams;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.managers.MapObjectManager;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.spring.TiledTestConfiguration;

@ContextConfiguration(classes = TiledTestConfiguration.class)
public class TestMetaFactory extends ContextBasedTest implements ApplicationEventPublisherAware{

    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private MapLoader mapLoader;
    
    @Autowired
    private MapRegistry mapRegistry;
    
    @Autowired
    private MapObjectManager mapObjectManager;
    
    
    @Before
    public void setup () {
        Assert.assertNotNull(mapLoader);
        
        loadAssets();
        Assert.assertEquals (1, mapRegistry.getRegisteredMaps().size());
    }    
    
    @Test
    public void testBuildEntity () {
        TiledMap map = mapRegistry.getRegisteredMaps().get("testMap");
        Assert.assertNotNull (map);
        mapObjectManager.setMapInfo(new TiledMapInfo (map));
        MapLayer objectLayer = map.getLayers().get(MapObjectManager.OBJECT_LAYER_NAME);
        assertEquals (1, objectLayer.getObjects().getCount());
        
        TextureMapObjectSpawnParams params = new TextureMapObjectSpawnParams();
        params.setId(UiManager.BLANK_IMAGE);
        eventPublisher.publishEvent(new SpawnEntityEvent(this, TextureMapObject.class, params));
        assertEquals (2, objectLayer.getObjects().getCount());
    }
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
