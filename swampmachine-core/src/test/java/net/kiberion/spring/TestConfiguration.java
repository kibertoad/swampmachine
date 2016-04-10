package net.kiberion.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.headless.HeadlessFiles;

import net.kiberion.assets.GameConfig;
import net.kiberion.assets.UiManager;

@Configuration
@ComponentScan("net.kiberion")
@Import(CoreConfiguration.class) 
public class TestConfiguration {



    public TestConfiguration() {
        GameConfig.config.setPathToResourcesAsString("src/test/resources/");
        
        Gdx.files = new HeadlessFiles();
        Gdx.gl = new HeadlessGl();
        
        AssetManager assetManager = new HeadlessAssetManager(); 
        UiManager.instance().setAssets(assetManager);
    }
    
}
