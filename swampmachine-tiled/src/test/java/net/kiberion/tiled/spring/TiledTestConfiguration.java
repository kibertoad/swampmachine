package net.kiberion.tiled.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.mvc.model.helpers.SpawningHelper;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.tiled.MapRegistry;
import net.kiberion.tiled.entityblocks.holders.MapMetadataHolderBlock;
import net.kiberion.tiled.factories.impl.TextureMapObjectFactory;
import net.kiberion.tiled.loaders.MapLoader;
import net.kiberion.tiled.loaders.NavTmxMapLoader;
import net.kiberion.tiled.managers.OrthographicMapObjectManager;
import net.kiberion.tiled.processors.CollisionInfoProvider;
import net.kiberion.tiled.processors.SimpleCollisionInfoProvider;

//ToDo Split TiledGameConfiguration so that part of it could be included here
@Configuration
public class TiledTestConfiguration {

    
    @Bean
    public MapLoader mapLoader (){
        return new MapLoader();
    }

    @Bean
    public MapRegistry mapRegistry (){
        return new MapRegistry();
    }
    
    @Bean
    public OrthographicMapObjectManager mapObjectManager (){
        return new OrthographicMapObjectManager();
    }

    @Bean
    public SpawningHelper spawningHelper (){
        return new SpawningHelper();
    }

    
    @Bean
    public TextureMapObjectFactory textureMapObjectFactory (){
        return new TextureMapObjectFactory();
    }
    
    @Bean
    public AbstractTiledMapModel<MapMetadataHolderBlock> testMapModel () {
        return new AbstractTiledMapModel<MapMetadataHolderBlock>() {

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
