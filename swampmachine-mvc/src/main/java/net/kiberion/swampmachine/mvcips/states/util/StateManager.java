package net.kiberion.swampmachine.mvcips.states.util;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.badlogic.gdx.Game;

import lombok.Setter;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.events.ChangeStateEvent;

public class StateManager implements ApplicationListener<ChangeStateEvent>{

    @Setter
    private Game game;
    
    @Autowired
    private StateRegistry stateRegistry;
    
    public void setState (GameState state) {
        game.setScreen(state);
    }
    
    @Override
    public void onApplicationEvent(ChangeStateEvent event) {
        GameState state = stateRegistry.getStates().get(event.getStateCode());
        Validate.notNull(state, "Unknown state "+event.getStateCode());
        setState (state);
    }
    
}
