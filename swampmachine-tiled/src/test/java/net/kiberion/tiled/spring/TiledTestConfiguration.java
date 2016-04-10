package net.kiberion.tiled.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.assets.UiManager;
import net.kiberion.tiled.factories.impl.TextureMapObjectFactory;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.loaders.NavTmxMapLoader;
import net.kiberion.tiled.managers.OrthographicMapObjectManager;

@Configuration
public class TiledTestConfiguration {

    
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
    
    
    public TiledTestConfiguration() {
        AssetManager assetManager = UiManager.instance().assets();
        assetManager.setLoader(TiledMap.class, new NavTmxMapLoader());
    }
    
}
