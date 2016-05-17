package net.kiberion.swampmachine.mvcips.states.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.mvcips.states.GameState;

public class StateRegistry {

    private static final Logger log = LogManager.getLogger();
    
    @Getter
    private final Map<String, GameState> states = new HashMap<>();

    @Getter
    @Setter
    private GameState loadingState; //called first

    @Getter
    @Setter
    private GameState startingState; //called after loading phase
    
    
    public void registerState (GameState state) {
        log.info("Registering state: "+state.getKey());
    	states.put(state.getKey(), state);
    }
	
}
