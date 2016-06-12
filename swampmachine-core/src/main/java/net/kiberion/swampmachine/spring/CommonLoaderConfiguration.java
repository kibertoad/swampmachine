package net.kiberion.swampmachine.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.assets.loaders.impl.ImageLoader;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.loaders.LoaderHelper;
import net.kiberion.swampmachine.loaders.ResourcesLoader;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.registries.CommonViewInfoRegistry;
import net.kiberion.swampmachine.registries.ImageRegistry;

@Configuration
public class CommonLoaderConfiguration {

    @Bean
    public LoaderHelper loaderHelper() {
        return new LoaderHelper();
    }
    
    @Bean
    public ResourcesLoader resourcesLoader() {
        ResourcesLoader loader = new ResourcesLoader();
        loader.setPriority(20); //should be loaded fairly early, because many entities depend on it
        return loader;
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

    @Bean
    public ImageRegistry imageRegistry() {
        return new ImageRegistry();
    }

    @Bean
    public ImageLoader imageLoader() {
        return new ImageLoader();
    }
    
}
