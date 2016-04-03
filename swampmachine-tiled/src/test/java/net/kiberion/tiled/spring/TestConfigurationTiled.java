package net.kiberion.tiled.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.assets.UiManager;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.loaders.NavTmxMapLoader;

@Configuration
public class TestConfigurationTiled {

    
    @Bean
    public MapLoader mapLoader (){
        return new MapLoader();
    }
    
    public TestConfigurationTiled() {
        AssetManager assetManager = UiManager.instance().assets();
        assetManager.setLoader(TiledMap.class, new NavTmxMapLoader());
    }
    
}
