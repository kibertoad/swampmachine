package net.kiberion.states;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;

import lombok.Getter;

@Singleton
public class StateRegistry {

    @Getter
    private final Map<String, GameState> states = new HashMap<>();

    public void registerState (GameState state) {
    	states.put(state.getKey(), state);
    }
	
}
