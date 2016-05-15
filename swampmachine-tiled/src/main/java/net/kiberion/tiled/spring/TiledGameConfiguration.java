package net.kiberion.tiled.spring;

import org.apache.commons.lang3.Validate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.mvc.model.helpers.SpawningHelper;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.tiled.MapRegistry;
import net.kiberion.tiled.factories.impl.TextureMapObjectFactory;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.loaders.NavTmxMapLoader;
import net.kiberion.tiled.managers.OrthographicMapObjectManager;
import net.kiberion.tiled.processors.CreatureCollisionProcessor;
import net.kiberion.tiled.processors.WallObstacleProcessor;

@Configuration
public class TiledGameConfiguration {

    
    @Bean
    public MapLoader mapLoader (){
        return new MapLoader();
    }
    
    @Bean
    public OrthographicMapObjectManager mapObjectManager (){
        return new OrthographicMapObjectManager();
    }

    @Bean
    public TextureMapObjectFactory textureMapObjectFactory (){
        return new TextureMapObjectFactory();
    }

    @Bean
    public SpawningHelper spawningHelper (){
        return new SpawningHelper();
    }

    @Bean
    public WallObstacleProcessor wallObstacleProcessor (){
        return new WallObstacleProcessor();
    }

    @Bean
    public MapRegistry mapRegistry (){
        return new MapRegistry();
    }
    
    @SuppressWarnings("rawtypes")
    @Bean
    public CreatureCollisionProcessor<?> creatureCollisionProcessor (){
        return new CreatureCollisionProcessor();
    }
    
    
    
    public TiledGameConfiguration() {
        AssetManager assetManager = UiManager.instance().getAssetManager();
        Validate.notNull(assetManager, "Asset manager is null. Probably GDX context not yet initialized.");
        assetManager.setLoader(TiledMap.class, new NavTmxMapLoader());
    }
    
}
