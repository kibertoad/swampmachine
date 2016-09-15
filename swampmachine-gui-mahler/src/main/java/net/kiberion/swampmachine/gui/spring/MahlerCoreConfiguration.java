package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.gui.composer.CompositionRegistry;

@Configuration
public class MahlerCoreConfiguration {
    
    @Bean
    public CompositionRegistry compositionRegistry () {
        return new CompositionRegistry();
    }
    
}
