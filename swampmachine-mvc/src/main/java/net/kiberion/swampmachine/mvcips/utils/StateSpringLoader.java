package net.kiberion.swampmachine.mvcips.utils;

import java.util.Collection;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.LoadingState;
import net.kiberion.swampmachine.mvcips.states.annotations.RealtimeProcessors;
import net.kiberion.swampmachine.mvcips.states.annotations.StartingState;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;
import net.kiberion.swampmachine.processors.TimedProcessor;

public class StateSpringLoader {

    private static final Logger log = LogManager.getLogger();

    private StateSpringLoader() {
    }

    /**
     * 
     * @param context
     * @return Sorted TreeSet
     */
    public static void registerStatesFromContext(ApplicationContext context, StateRegistry stateRegistry) {
        log.info("Start registering game states from Spring context.");
        Collection<GameState> stateBeans = context.getBeansOfType(GameState.class).values();

        for (GameState bean : stateBeans) {
            if (bean.getClass().isAnnotationPresent(LoadingState.class)) {
                if (stateRegistry.getLoadingState() != null) {
                    throw new IllegalStateException(
                            "More than one loading state exist: " + stateRegistry.getLoadingState() + ", " + bean);
                }
                stateRegistry.setLoadingState(bean);
            }
            if (bean.getClass().isAnnotationPresent(StartingState.class)) {
                if (stateRegistry.getStartingState() != null) {
                    throw new IllegalStateException(
                            "More than one starting state exist: " + stateRegistry.getStartingState() + ", " + bean);
                }
                stateRegistry.setStartingState(bean);
            }

            stateRegistry.registerState(bean);

            // Attach realtime processors
            RealtimeProcessors realtimeProcessors = bean.getClass().getAnnotation(RealtimeProcessors.class);
            if (realtimeProcessors != null) {
                for (Class<? extends TimedProcessor> clazz : realtimeProcessors.beansOfClasses()) {
                    TimedProcessor processor = context.getBean(clazz);
                    bean.getRealtimeProcessors().add(processor);
                }
            }

        }

        Validate.notNull(stateRegistry.getLoadingState(), "Loading state is null");
        Validate.notNull(stateRegistry.getStartingState(), "Starting state is null");

        log.info("Done registering game states from Spring context.");
    }

}
