package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.factories.MetaFactory;

/**
 * Recommended but optional Swampmachine beans
 * 
 * @author kibertoad
 *
 */

@Configuration
public class SwampmachineExtrasConfiguration {

    @Bean
    public MetaFactory metaFactory() {
        return new MetaFactory();
    }

    
    
    
}
