package net.kiberion.swampmachine.mvcips.states;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.api.common.RealtimeUpdatable;
import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.gui.view.StateView;
import net.kiberion.swampmachine.mvcips.input.GenericInputAdapter;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.annotations.StateControllers;
import net.kiberion.swampmachine.mvcips.states.annotations.SubView;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;
import net.kiberion.swampmachine.mvcips.utils.UpdatableStageWrapper;
import net.kiberion.swampmachine.processors.AbstractTimedProcessor;

/**
 * MVCIPS stands for "Model View Controller Input adapter Processor State"
 * 
 * Model and view roles in Swampmachine are no different from their counterparts
 * in traditional MVC pattern; Input adapters consume input from user and
 * translates it into calls to controller when appropriate; Processors apply
 * predefined set of analysis rules on current model state and translates
 * outcome into calls to controller when appropriate; Controllers modify both
 * model and view based on instructions received (either from an input adapter
 * or processor); State is a container for model, view (and subviews), input
 * adapters, controllers and processors for a given game state.
 * 
 * 
 * @author kibertoad
 *
 */
public abstract class GameState implements Screen, InitializingBean {

    @Getter
    @Setter
    private InputAdapter input;

    @Getter
    private final List<AbstractTimedProcessor> realtimeProcessors = new ArrayList<>();

    @Getter
    private final List<AbstractStateController> stateControllers = new ArrayList<>();

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ScriptEntityFactory scriptEntityFactory;

    // used for stacking multiple input adapters
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();

    private boolean guiInitted;

    public List<RealtimeUpdatable> entitiesForUpdate = new ArrayList<>();

    private SwampBinding binding;

    // Includes subviews
    private final Map<String, StateView> allSubViews = new LinkedHashMap<>();

    public GameState() {
        InputAdapter adapter = initInputAdapter();
        if (adapter != null) {
            this.setInput(adapter);
        }
    }

    protected InputAdapter initInputAdapter() {
        return new GenericInputAdapter();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        binding = scriptEntityFactory.getBindingInstance();
        StateControllers stateControllers = getClass().getAnnotation(StateControllers.class);

        if (stateControllers != null) {
            for (Class<? extends AbstractStateController> controllerClass : stateControllers.controllers()) {
                AbstractStateController controller = context.getBean(controllerClass);
                StateController controllerMetadata = controller.getClass().getAnnotation(StateController.class);
                getStateControllers().add(controller);

                if (binding.hasVariable(controllerMetadata.nameInBinding())) {
                    throw new IllegalStateException(
                            "Conflicting bindings exist for controller " + controllerMetadata.nameInBinding());
                }

                binding.setVariable(controllerMetadata.nameInBinding(), controller);
            }
        }

        allSubViews.clear();
        for (StateView stateView : getAllViews()) {
            // map subViews for later access e. g. via events
            SubView subViewMetadata = stateView.getClass().getAnnotation(SubView.class);
            if (subViewMetadata != null) {
                allSubViews.put(subViewMetadata.id(), stateView);
            }

            // add state binding for invoking scripts inside the view
            stateView.setBinding(binding);
        }
    }

    public void showView(String viewId, boolean disableOtherSubViews) {
        StateView subViewToEnable = allSubViews.get(viewId);
        Validate.notNull(subViewToEnable);
        subViewToEnable.show();

        if (disableOtherSubViews) {
            for (StateView view : allSubViews.values()) {
                if (view != subViewToEnable) {
                    view.hide();
                }
            }
        }
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
        for (AbstractStateController controller : stateControllers) {
            controller.show();
        }

        getView().show();
        addProcessorsForAllViews();
        ((AbstractStateView) getView()).debugToLog();
    }

    /**
     * Use composition injection and postInjection instead
     */
    @Deprecated
    public void initGUIElements() {
        Validate.isTrue(!guiInitted);
        getView().initGUIElements();
        guiInitted = true;
        Set<Stage> stages = getAllStages();
        for (Stage stage : stages) {
            entitiesForUpdate.add(new UpdatableStageWrapper(stage));
        }
    }

    protected Set<Stage> getAllStages() {
        Set<Stage> result = new HashSet<>();
        getView().collectAllStages(result);
        return result;
    }

    protected Set<StateView> getAllViews() {
        Set<StateView> result = new HashSet<>();

        StateView view = getView();
        if (view != null) {
            view.collectAllViews(result);
        }
        return result;
    }

    @Override
    public void render(float delta) {
        for (RealtimeUpdatable entity : entitiesForUpdate) {
            entity.update(delta);
        }

        for (AbstractTimedProcessor processor : realtimeProcessors) {
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

    // don't forget to set View stage from gamestate stage
    public abstract StateView getView();

}
