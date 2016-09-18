package net.kiberion.swampmachine.mvcips.states;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.entities.common.api.RealtimeUpdatable;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.gui.view.StateView;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.utils.UpdatableStageWrapper;
import net.kiberion.swampmachine.processors.TimedProcessor;

/**
 * MVCIPS stands for "Model View Controller Input adapter Processor State"
 * 
 * Model and view roles in Swampmachine are no different from their counterparts in traditional MVC pattern;
 * Input adapters consume input from user and translates it into calls to controller when appropriate;
 * Processors apply predefined set of analysis rules on current model state and translates outcome into calls to controller when appropriate;
 * Controllers modify both model and view based on instructions received (either from an input adapter or processor);
 * State is a container for model, view (and subviews), input adapters, controllers and processors for a given game state.
 *  
 * 
 * @author kibertoad
 *
 */
public abstract class GameState implements Screen, InitializingBean {

    @Getter
    @Setter
    private InputAdapter input;

    private InputMultiplexer inputMultiplexer = new InputMultiplexer(); //used for stacking multiple input adapters
    
    private boolean guiInitted;

    public List<RealtimeUpdatable> entitiesForUpdate = new ArrayList<>();
    
    @Getter
    private final List<TimedProcessor> realtimeProcessors = new ArrayList<>();  


    @Override
    public void afterPropertiesSet() throws Exception {
    }
    
    protected void addProcessorsForAllViews() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        inputMultiplexer.clear();

        for (Stage stage : getAllStages()) {
            inputMultiplexer.addProcessor(stage);
        }
        
        if (input != null) {
            inputMultiplexer.addProcessor(input);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void show() {
        getView().show();
        addProcessorsForAllViews();
        ((AbstractStateView) getView()).debugToLog();
    }

    /**
     * Use composition injection and postInjection instead
     */
    @Deprecated
    public void initGUIElements () {
        Validate.isTrue(!guiInitted);
        getView().initGUIElements();
        guiInitted = true;
        Set<Stage> stages = getAllStages();
        for (Stage stage : stages) {
            entitiesForUpdate.add(new UpdatableStageWrapper(stage));
        }
    }
    
    protected Set<Stage> getAllStages () {
        Set<Stage> result = new HashSet<>();
        getView().collectAllStages (result);
        return result;
    }

    @Override
    public void render(float delta) {
        for (RealtimeUpdatable entity : entitiesForUpdate) {
            entity.update(delta);
        }
        
        for (TimedProcessor processor : realtimeProcessors) {
            processor.update(delta);
        }

        getView().act(delta);
        getView().render();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        getView().hide();
    }

    @Override
    public void dispose() {
    }
    
    public String getId() {
        return getClass().getAnnotation(State.class).id();
    }
    
    public abstract StateView getView(); //don't forget to set View stage from gamestate stage

}
