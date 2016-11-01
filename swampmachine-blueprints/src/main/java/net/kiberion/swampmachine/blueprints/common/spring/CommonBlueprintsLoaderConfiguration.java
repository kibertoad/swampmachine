package net.kiberion.swampmachine.blueprints.common.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.blueprints.common.loaders.CommonViewInfoLoader;
import net.kiberion.swampmachine.blueprints.common.loaders.CreatureLoader;
import net.kiberion.swampmachine.blueprints.common.registries.CreatureRegistry;

@Configuration
public class CommonBlueprintsLoaderConfiguration {

    @Bean
    public CreatureLoader creatureLoader() {
        return new CreatureLoader();
    }

    @Bean
    public CreatureRegistry creatureRegistry() {
        return new CreatureRegistry();
    }
    
    @Bean
    public CommonViewInfoLoader viewInfoLoader() {
        return new CommonViewInfoLoader();
    }
    
}
