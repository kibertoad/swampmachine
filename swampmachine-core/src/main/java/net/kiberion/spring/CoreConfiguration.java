package net.kiberion.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.kiberion.assets.GameConfig;
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
        GameConfigLoader configLoader = new GameConfigLoader(gameConfig);
        configLoader.load();
        FileReaderFactory.setConfig(gameConfig);
    }

}
