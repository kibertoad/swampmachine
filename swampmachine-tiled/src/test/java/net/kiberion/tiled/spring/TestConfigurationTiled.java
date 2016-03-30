package net.kiberion.tiled.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.tiled.loaders.MapLoader;

@Configuration
public class TestConfigurationTiled {

    
    @Bean
    public MapLoader mapLoader (){
        return new MapLoader();
    }
    
}
