package net.kiberion.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import net.kiberion.assets.GameConfig;
import net.kiberion.assets.UiManager;

@Configuration
@ComponentScan("net.kiberion")
@Import(CoreConfiguration.class) 
public class TestConfiguration {



    public TestConfiguration() {
        GameConfig.config.setPathToResourcesAsString("src/test/resources/");
        
        Gdx.files = new HeadlessFiles();
        AssetManager assetManager = new AssetManager(); 
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        UiManager.instance().setAssets(assetManager);
    }
    
}
