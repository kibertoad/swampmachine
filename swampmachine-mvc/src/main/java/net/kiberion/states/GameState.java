package net.kiberion.states;

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
import net.kiberion.entities.common.api.DeltaUpdatable;
import net.kiberion.mvc.api.StateView;
import net.kiberion.processors.TimedProcessor;

public abstract class GameState implements Screen, InitializingBean {

    @Getter
    private final String key;

    @Getter
    @Setter
    private Stage stage;

    @Getter
    @Setter
    private Stage overlayStage;

    @Getter
    @Setter
    private InputAdapter input;

    private InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public List<DeltaUpdatable> entitiesForUpdate = new ArrayList<>();
    private List<? extends StateView> subViews = new ArrayList<>();

    @Getter
    private final List<TimedProcessor> realtimeProcessors = new ArrayList<>();  

    public GameState(String key) {
        super();
        this.key = key;
        this.stage = new Stage();

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
        for (DeltaUpdatable entity : entitiesForUpdate) {
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
