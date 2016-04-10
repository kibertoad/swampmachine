package net.kiberion.states.util;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Game;

import lombok.Setter;
import net.kiberion.states.GameState;
import net.kiberion.states.events.ChangeStateEvent;

@Component
public class StateManager implements ApplicationEventPublisherAware, ApplicationListener<ChangeStateEvent>{

    @Setter
    private Game game;
    
    @Autowired
    private StateRegistry stateRegistry;
    
    private ApplicationEventPublisher eventPublisher;
    
    public void setState (GameState state) {
        game.setScreen(state);
    }
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onApplicationEvent(ChangeStateEvent event) {
        GameState state = stateRegistry.getStates().get(event.getStateCode());
        Validate.notNull(state, "Unknown state "+event.getStateCode());
        setState (state);
    }
    
}
