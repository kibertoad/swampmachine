package net.kiberion.swampmachine.mvcips.states;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.entities.common.api.RealtimeUpdatable;
import net.kiberion.swampmachine.mvcips.view.StateView;
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
    private final String key;

    @Getter
    @Setter
    private Stage stage;

    @Getter
    @Setter
    private Stage overlayStage; //stage that is rendered on top of main stage, typically used for displaying auxiliary UI elements on top of main view

    @Getter
    @Setter
    private InputAdapter input;

    private InputMultiplexer inputMultiplexer = new InputMultiplexer(); //used for stacking multiple input adapters

    public List<RealtimeUpdatable> entitiesForUpdate = new ArrayList<>();
    private List<? extends StateView> subViews = new ArrayList<>();

    @Getter
    private final List<TimedProcessor> realtimeProcessors = new ArrayList<>();  

    public GameState(String key) {
        super();
        this.key = key;
        this.stage = new Stage();

    }
    
    protected GameState(String key, Stage stage) {
        super();
        this.key = key;
        this.stage = stage;
    }

    public void addSubView(StateView view) {
        view.setStage(stage);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (getView() != null) {
            getView().setStage(stage);
            getView().initGUIElements();
        }
    }

    @Override
    public void show() {
        getView().show();

        Gdx.input.setInputProcessor(inputMultiplexer);
        inputMultiplexer.clear();

        inputMultiplexer.addProcessor(getStage());
        if (getOverlayStage() != null) {
            inputMultiplexer.addProcessor(getOverlayStage());
        }

        if (input != null) {
            inputMultiplexer.addProcessor(input);
        }
    }

    @Override
    public void render(float delta) {
        for (RealtimeUpdatable entity : entitiesForUpdate) {
            entity.update(delta);
        }
        
        for (TimedProcessor processor : realtimeProcessors) {
            processor.update(delta);
        }
        

        if (getStage() != null) {
            getStage().act(delta);
            getStage().draw();
        }

        if (getOverlayStage() != null) {
            getOverlayStage().act(delta);
            getOverlayStage().draw();
        }

        for (StateView view : subViews) {
            view.act(delta);
        }
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

        for (StateView subView : subViews) {
            subView.hide();
        }
    }

    @Override
    public void dispose() {
    }
    
    public abstract StateView getView(); //don't forget to set View stage from gamestate stage

}
