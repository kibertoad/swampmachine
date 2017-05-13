package spring;

import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesStorage;
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
import org.springframework.context.annotation.Scope;

@Configuration
public class CommonLoaderConfiguration {

    @Bean
    public LoaderHelper loaderHelper() {
        return new LoaderHelper();
    }

    @Bean
    public ResourcesLoader resourcesLoader() {
        // resources loader is not launched automatically, hence doesn't need priority
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

    @Bean
    @Scope("prototype")
    //resourceLoader is needed to make it sure it was created and loaded before the injection happens
    public ResourcesStorage resourceStorageInstance (ResourceRegistry resourceRegistry, ResourcesLoader resourceLoader) {
        return new ResourcesStorage (resourceRegistry.getExistingResources());
    }


}
