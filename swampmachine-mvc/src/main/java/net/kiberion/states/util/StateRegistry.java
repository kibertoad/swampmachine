package net.kiberion.states.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.states.GameState;

@Component
public class StateRegistry {

    @Getter
    private final Map<String, GameState> states = new HashMap<>();

    @Getter
    @Setter
    private GameState loadingState; //called first

    @Getter
    @Setter
    private GameState startingState; //called after loading phase
    
    
    public void registerState (GameState state) {
    	states.put(state.getKey(), state);
    }
	
}
