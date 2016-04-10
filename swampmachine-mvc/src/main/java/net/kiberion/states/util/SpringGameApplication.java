package net.kiberion.states.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import net.kiberion.mvc.spring.StateSpringLoader;

public class SpringGameApplication extends GameApplication{

    @Getter
    private StateRegistry stateRegistry;
    private StateManager stateManager;
    
    protected void initContext (Class<?> configurationClass) {
        if (configurationClass.getAnnotation(Configuration.class) == null) {
            throw new IllegalArgumentException(configurationClass.getCanonicalName()+" is not a Configuration class.");
        }
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(configurationClass);

        stateRegistry = ctx.getBean(StateRegistry.class);
        StateSpringLoader.loadStatesFromContext(ctx, stateRegistry);
        
        stateManager = ctx.getBean(StateManager.class);
        stateManager.setGame(this);
    }
    
}
