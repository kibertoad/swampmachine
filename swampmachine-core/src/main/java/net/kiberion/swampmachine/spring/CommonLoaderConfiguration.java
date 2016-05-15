package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.loaders.CommonViewInfoLoader;
import net.kiberion.swampmachine.loaders.CreatureLoader;
import net.kiberion.swampmachine.loaders.LoaderHelper;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.registries.CommonViewInfoRegistry;

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
    public ReaderHelper readerHelper() {
        return new ReaderHelper();
    }
    
    @Bean
    public CommonViewInfoLoader viewInfoLoader() {
        return new CommonViewInfoLoader();
    }
    
    @Bean
    public CommonModelInfoRegistry modelInfoRegistry() {
        return new CommonModelInfoRegistry();
    }
    
    @Bean
    public CommonViewInfoRegistry viewInfoRegistry() {
        return new CommonViewInfoRegistry();
    }

}
