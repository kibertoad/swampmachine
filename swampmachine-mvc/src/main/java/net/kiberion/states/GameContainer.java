package net.kiberion.states;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lombok.Getter;
import net.kiberion.assets.CommonModelInfoLoader;
import net.kiberion.assets.registries.LoaderRegistry;
import net.kiberion.blueprint.common.registries.CommonModelInfoRegistry;

/**
 * Container clas for game stats and global registries (for game assets, map, model info etc)
 * @author user
 *
 */

@Singleton
public class GameContainer implements Module{

    @Getter
    private GameState<?, ?> activeState;

    @Getter
    @Inject
    private StateRegistry stateRegistry;
    
    @Getter
    @Inject
    private LoaderRegistry loaderRegistry;
    
    @Getter
    @Inject
    private CommonModelInfoRegistry commonModelInfoRegistry;
    
    @Inject
    @Getter
    private CommonModelInfoLoader modelInfoProvider;
    
    /**
     * This method should be called after injection was already performed
     * @param state
     */
    public void addState(GameState<?, ?> state) {
        if (!stateRegistry.getStates().containsValue(state)) {
        	stateRegistry.getStates().put (state.getKey(), state);
            state.postInjection();
        }
    }   
    
    
    public void setActiveState (String key) {
        GameState<?, ?> state = stateRegistry.getStates().get(key);
        
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
