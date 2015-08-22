package net.kiberion.states;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lombok.Getter;

@Singleton
public class GameContainer implements Module{

    @Getter
    private GameState activeState;
    
    @Getter
    private final Map<String, GameState> states = new HashMap<>();

    /**
     * This method should be called after injection was already performed
     * @param state
     */
    public void addState(GameState state) {
        if (!states.containsValue(state)) {
            states.put (state.getKey(), state);
            state.postInjection();
        }
    }   
    
    
    public void setActiveState (String key) {
        GameState state = states.get(key);
        
        if (state == null) {
            throw new IllegalArgumentException ("Unknown state: "+key);
        }
        
        if (activeState != null) {
            activeState.hide();
        }
        activeState = state;
        activeState.show();
    }



    @Override
    public void configure(Binder binder) {
    }
    
    
    
}
