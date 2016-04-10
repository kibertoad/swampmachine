package net.kiberion.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.assets.AssetManager;

import net.kiberion.assets.GameConfig;
import net.kiberion.assets.UiManager;
import net.kiberion.assets.loaders.impl.GameConfigLoader;
import net.kiberion.assets.loaders.util.FileReaderFactory;

@Configuration
@ComponentScan("net.kiberion")
public class CoreConfiguration {

    GameConfig gameConfig = GameConfig.config;

    @Bean
    public GameConfig gameConfig() {
        return gameConfig;
    }

    public CoreConfiguration() {
        AssetManager assetManager = new AssetManager();
        UiManager.instance().setAssets(assetManager);

        GameConfigLoader configLoader = new GameConfigLoader(gameConfig);
        configLoader.load();
        FileReaderFactory.setConfig(gameConfig);
    }

}
