package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.loaders.CommonViewInfoLoader;
import net.kiberion.swampmachine.loaders.CreatureLoader;
import net.kiberion.swampmachine.loaders.LoaderHelper;

@Configuration
public class CommonLoaderConfiguration {

    @Bean
    public CreatureLoader creatureLoader() {
        return new CreatureLoader();
    }

    @Bean
    public LoaderHelper loaderHelper() {
        return new LoaderHelper();
    }

    @Bean
    public CommonViewInfoLoader viewInfoLoader() {
        return new CommonViewInfoLoader();
    }
    
    
    
}
