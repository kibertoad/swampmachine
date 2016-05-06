package net.kiberion.states.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import net.kiberion.assets.loaders.api.AssetLoader;
import net.kiberion.assets.loaders.util.AssetLoaderSpringExtractor;
import net.kiberion.mvc.spring.StateSpringLoader;

public abstract class SpringGameApplication extends GameApplication{

    private static final Logger log = LogManager.getLogger();
    
    @Getter
    private StateRegistry stateRegistry;
    private StateManager stateManager;
    
    protected abstract Class<?> getConfigurationClass ();
    
    protected void initContext () {
        initContext(getConfigurationClass());
    }
    
    @Override
    public void create() {
        super.create();
        loadConfig();
        initContext();
    }
    
    protected void initContext (Class<?> configurationClass) {
        log.info("Start loading Spring context.");
        if (configurationClass.getAnnotation(Configuration.class) == null) {
            throw new IllegalArgumentException(configurationClass.getCanonicalName()+" is not a Configuration class.");
        }
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(configurationClass);
        log.info("Done loading Spring context.");

        stateRegistry = ctx.getBean(StateRegistry.class);
        StateSpringLoader.registerStatesFromContext(ctx, stateRegistry);
        
        stateManager = ctx.getBean(StateManager.class);
        stateManager.setGame(this);
        
        List<AssetLoader> preStartupLoaders = AssetLoaderSpringExtractor.extractSortedPreStartupAssetLoadersFromContext(ctx);
        for (AssetLoader loader : preStartupLoaders) {
            loader.load();
        }        
        
    }
    
}
