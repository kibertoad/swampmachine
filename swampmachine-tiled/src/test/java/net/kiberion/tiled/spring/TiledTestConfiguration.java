package net.kiberion.tiled.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.tiled.aspects.holders.MapMetadataHolderAspect;
import net.kiberion.tiled.factories.impl.TextureMapObjectFactory;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.loaders.NavTmxMapLoader;
import net.kiberion.tiled.managers.OrthographicMapObjectManager;
import net.kiberion.tiled.processors.CollisionInfoProvider;
import net.kiberion.tiled.processors.SimpleCollisionInfoProvider;

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
    
    @Bean
    public AbstractTiledMapModel<MapMetadataHolderAspect> testMapModel () {
        return new AbstractTiledMapModel<MapMetadataHolderAspect>() {

            @Override
            protected void placeCreatures() {
            }
        };
    }
    
    @Bean
    public CollisionInfoProvider collisionInfoProvider () {
        return new SimpleCollisionInfoProvider ();        
    }
    
    public TiledTestConfiguration() {
        AssetManager assetManager = UiManager.instance().getAssetManager();
        assetManager.setLoader(TiledMap.class, new NavTmxMapLoader());
    }
    
}
