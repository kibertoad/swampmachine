package net.kiberion.swampmachine.mvcips.states.util;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import com.badlogic.gdx.Game;

import lombok.Setter;
import net.kiberion.swampmachine.events.ChangeStateEvent;
import net.kiberion.swampmachine.events.ShowSubViewEvent;
import net.kiberion.swampmachine.mvcips.states.GameState;

public class StateManager {

    private static final Logger log = LogManager.getLogger();

    @Setter
    private Game game;
    
    @Autowired
    private StateRegistry stateRegistry;
    
    public void setState (GameState state) {
        log.info("Transitioning to state: "+state.getId());
        game.setScreen(state);
    }

    @EventListener
    public void showSubView(ShowSubViewEvent event) {
        GameState currentState = (GameState) game.getScreen();
        currentState.showView(event.getSubViewId(), event.isHideOther());
    }
    
    
    @EventListener
    public void changeState(ChangeStateEvent event) {
        GameState state = stateRegistry.getStates().get(event.getStateCode());
        Validate.notNull(state, "Unknown state "+event.getStateCode());
        setState (state);
    }
    
}
