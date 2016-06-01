package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.loaders.AbstractLoader;
import net.kiberion.swampmachine.loaders.LoaderHelper;
import net.kiberion.swampmachine.loaders.ResourcesLoader;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.registries.CommonViewInfoRegistry;

@Configuration
public class CommonLoaderConfiguration {

    @Bean
    public LoaderHelper loaderHelper() {
        return new LoaderHelper();
    }
    
    @Bean
    public AbstractLoader resourcesLoader() {
        return new ResourcesLoader();
    }
    

    @Bean
    public ReaderHelper readerHelper() {
        return new ReaderHelper();
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
