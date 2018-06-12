package net.kiberion.swampmachine.spring;

import net.kiberion.swampmachine.factories.MetaFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
