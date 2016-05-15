package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.assets.loaders.impl.GameConfigLoader;
import net.kiberion.swampmachine.assets.loaders.util.FileReaderFactory;

@Configuration
public class CoreConfiguration {

    GameConfig gameConfig = GameConfig.config;

    @Bean
    public GameConfig gameConfig() {
        return gameConfig;
    }

    public CoreConfiguration() {
        GameConfigLoader configLoader = new GameConfigLoader(gameConfig);
        configLoader.load();
        FileReaderFactory.setConfig(gameConfig);
        initAssetManager();
    }
    
    protected void initAssetManager () {
        AssetManager assetManager = new AssetManager();
        UiManager.instance().setAssetManager(assetManager);
    }

}
