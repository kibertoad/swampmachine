package net.kiberion.swampmachine.mvcips.states.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;

public class StateRegistry {

    private static final Logger log = LogManager.getLogger();

    @Getter
    private final Map<String, GameState> states = new HashMap<>();

    @Getter
    @Setter
    private GameState loadingState; // called first

    @Getter
    @Setter
    private GameState startingState; // called after loading phase

    public void registerState(GameState state) {
        State stateAnnotation = state.getClass().getAnnotation(State.class);
        Validate.notNull(stateAnnotation,
                "@State annotation is not provided for state class " + state.getClass().getCanonicalName());
        log.info("Registering state: " + stateAnnotation.id());
        states.put(stateAnnotation.id(), state);
    }

}
