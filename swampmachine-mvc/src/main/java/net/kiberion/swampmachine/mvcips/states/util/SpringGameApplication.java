package net.kiberion.swampmachine.mvcips.states.util;

import java.util.Collection;
import java.util.List;

import net.kiberion.swampmachine.api.Initter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import net.kiberion.swampmachine.assets.loaders.api.Loader;
import net.kiberion.swampmachine.assets.loaders.util.LoaderSpringExtractor;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.utils.StateSpringLoader;
import net.kiberion.swampmachine.mvcips.utils.SubViewSpringBinder;

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
        SubViewSpringBinder.bindSubViewsFromContext(ctx);
        StateSpringLoader.registerStatesFromContext(ctx, stateRegistry);
        
        stateManager = ctx.getBean(StateManager.class);
        stateManager.setGame(this);
        
        List<Loader> preStartupLoaders = LoaderSpringExtractor.extractSortedPreStartupAssetLoadersFromContext(ctx);
        for (Loader loader : preStartupLoaders) {
            loader.load();
        }
        
        Collection<Initter> initters = ctx.getBeansOfType(Initter.class).values();
        for (Initter initter : initters) {
            initter.init();
        }
        
        for (GameState state: stateRegistry.getStates().values()) {
            state.initGUIElements();
        }
        
    }
    
}
