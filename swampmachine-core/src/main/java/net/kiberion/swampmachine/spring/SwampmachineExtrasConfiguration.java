package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesStorage;
import net.kiberion.swampmachine.factories.MetaFactory;
import net.kiberion.swampmachine.registries.ResourceRegistry;

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

    @Bean
    @Scope("prototype")
    public ResourcesStorage resourceStorageInstance (ResourceRegistry resourceRegistry) {
        return new ResourcesStorage (resourceRegistry.getExistingResources());
    }
    
    
    
}
