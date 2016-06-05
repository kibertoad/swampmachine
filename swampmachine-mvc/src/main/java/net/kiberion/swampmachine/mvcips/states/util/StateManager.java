package net.kiberion.swampmachine.mvcips.states.util;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.badlogic.gdx.Game;

import lombok.Setter;
import net.kiberion.swampmachine.events.ChangeStateEvent;
import net.kiberion.swampmachine.mvcips.states.GameState;

public class StateManager implements ApplicationListener<ChangeStateEvent>{

    private static final Logger log = LogManager.getLogger();

    @Setter
    private Game game;
    
    @Autowired
    private StateRegistry stateRegistry;
    
    public void setState (GameState state) {
        log.info("Transitioning to state: "+state.getId());
        game.setScreen(state);
    }
    
    @Override
    public void onApplicationEvent(ChangeStateEvent event) {
        GameState state = stateRegistry.getStates().get(event.getStateCode());
        Validate.notNull(state, "Unknown state "+event.getStateCode());
        setState (state);
    }
    
}
