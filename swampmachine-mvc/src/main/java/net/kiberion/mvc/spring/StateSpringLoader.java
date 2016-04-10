package net.kiberion.mvc.spring;

import java.util.Collection;

import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;

import net.kiberion.states.GameState;
import net.kiberion.states.annotations.LoadingState;
import net.kiberion.states.annotations.StartingState;
import net.kiberion.states.util.StateRegistry;

public class StateSpringLoader {

    private StateSpringLoader () {}
    
    /**
     * 
     * @param context
     * @return Sorted TreeSet
     */
    public static void loadStatesFromContext(ApplicationContext context, StateRegistry stateRegistry) {
        Collection<GameState> stateBeans = context.getBeansOfType(GameState.class).values();

        for (GameState bean : stateBeans) {
            if (bean.getClass().isAnnotationPresent(LoadingState.class)) {
                stateRegistry.setLoadingState(bean);
            }
            if (bean.getClass().isAnnotationPresent(StartingState.class)) {
                stateRegistry.setStartingState(bean);
            }

            stateRegistry.registerState(bean);
        }
        
        Validate.notNull(stateRegistry.getLoadingState(), "Loading state is null");
        Validate.notNull(stateRegistry.getStartingState(), "Starting state is null");
    }

}
