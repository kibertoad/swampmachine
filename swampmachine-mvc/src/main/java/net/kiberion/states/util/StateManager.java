package net.kiberion.states.util;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.Game;

import lombok.Setter;
import net.kiberion.states.GameState;

@Component
public class StateManager {

    @Setter
    private Game game;
    
    public void setState (GameState state) {
        game.setScreen(state);
    }
    
}
