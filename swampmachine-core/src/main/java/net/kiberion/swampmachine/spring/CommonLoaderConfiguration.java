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
import net.kiberion.swampmachine.registries.ResourceRegistry;

@Configuration
public class CommonLoaderConfiguration {

    @Bean
    public LoaderHelper loaderHelper() {
        return new LoaderHelper();
    }
    
    @Bean
    public ResourcesLoader resourcesLoader() {
        ResourcesLoader loader = new ResourcesLoader(); //resources loader is not launched automatically, hence doesn't need priority
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
    public ResourceRegistry resourceRegistry() {
        return new ResourceRegistry();
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
