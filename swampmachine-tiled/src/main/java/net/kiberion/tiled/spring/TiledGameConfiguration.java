package net.kiberion.tiled.spring;

import org.apache.commons.lang3.Validate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.tiled.factories.impl.TextureMapObjectFactory;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.loaders.NavTmxMapLoader;
import net.kiberion.tiled.managers.OrthographicMapObjectManager;

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
    
    
    public TiledGameConfiguration() {
        AssetManager assetManager = UiManager.instance().getAssetManager();
        Validate.notNull(assetManager, "Asset manager is null. Probably GDX context not yet initialized.");
        assetManager.setLoader(TiledMap.class, new NavTmxMapLoader());
    }
    
}
