package net.kiberion.swampmachine.gui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.annotations.SubView;
import net.kiberion.swampmachine.api.common.Recalculable;
import net.kiberion.swampmachine.api.elements.Label;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.api.view.StateView;
import net.kiberion.swampmachine.factories.InvokablesFactory;
import net.kiberion.swampmachine.gui.managers.GuiManager;

public abstract class AbstractStateView<T> implements StateView, Recalculable, InitializingBean {

    private static final Logger log = LogManager.getLogger();

    // Current level stage, used for elements in this view
    @Getter
    protected Stage mainStage;

    @Getter
    private T model;

    @Getter
    @Setter
    private SwampBinding binding;

    private GuiManager guiManager;

    @Autowired
    @Getter
    private InvokablesFactory invokablesFactory;

    @Getter
    private List<StateView> subViews = new ArrayList<>();

    private boolean isEnabled = true;

    @Autowired
    private Label coordsLabel;

    public AbstractStateView() {
        initStage();
    }

    protected void initStage() {
        mainStage = new Stage();
    }

    @Override
    public void addSubView(StateView view) {
        Validate.notNull(view);
        subViews.add(view);
    }

    @Override
    public void postInjection() {
        coordsLabel.setPosition(1200, 650);
        mainStage.addActor((Actor) coordsLabel);
    }

    @Autowired
    public void setGuiManager(GuiManager guiManager) {
        this.guiManager = guiManager;

        if (mainStage != null) {
            this.guiManager.setStage(mainStage);
        }
    }

    @Autowired
    public void setModel(T model) {
        this.model = model;
    }

    @Override
    public void hide() {
        log.info("Hiding: " + getClass().getCanonicalName());
        isEnabled = false;
    }

    protected void hideAllSubViews(Collection<AbstractStateSubView<?>> exceptions) {
        for (StateView subView : getSubViews()) {
            if (!exceptions.contains(subView)) {
                subView.hide();
            }
        }
    }

    protected void hideAllSubViews() {
        hideAllSubViews(Collections.emptyList());
    }

    protected void showSubViews(Collection<Class<? extends AbstractStateSubView<?>>> collection) {
        for (StateView subView : getSubViews()) {
            if (collection.contains(subView.getClass())) {
                subView.show();
            }
        }
    }

    private void sortSubViews () {
        Collections.sort(subViews, new Comparator<StateView>() {
            @Override
            public int compare(StateView subView1, StateView subView2)
            {
                SubView metadata1 = subView1.getClass().getAnnotation(SubView.class);
                SubView metadata2 = subView2.getClass().getAnnotation(SubView.class);
                return Integer.compare(metadata1.zIndex(), metadata2.zIndex());
            }
        });        
    }
    
    @Override
    public void show() {
        sortSubViews();
        hideAllSubViews();
        showSubViews (getAutoEnabledSubViews());
        if (isEnabled == false) {
            log.info("Showing: " + getClass().getCanonicalName());
            isEnabled = true;
        }
    }

    private void updateCoordsLabel() {
        if (coordsLabel != null) {
            coordsLabel.setText(Gdx.input.getX() + "/" + (Gdx.graphics.getHeight() - Gdx.input.getY()));
        }
    }

    @Override
    public void render() {
        if (isEnabled) {
            updateCoordsLabel();
            mainStage.draw();

            for (StateView subView : subViews) {
                subView.render();
            }
        }
    }

    @Override
    public void act(float delta) {
        if (isEnabled) {
            for (StateView subView : subViews) {
                subView.act(delta);
            }
        }

        /*
         * for (PyramideAnimation animation : animationList) {
         * animation.act(delta);
         * 
         * if (animation.isCompleted() && (!animation.isLooped())) {
         * animationGarbageList.add(animation); } }
         * 
         * while (!animationGarbageList.isEmpty()) {
         * animationList.remove(animationGarbageList.poll()); }
         * 
         * while (!animationAdditionList.isEmpty()) {
         * animationList.remove(animationAdditionList.poll()); }
         */

    }

    @Override
    public void initGUIElements() {
        Validate.notNull(mainStage, "Stage is null.");
        Validate.notNull(getGuiManager(), "GUIManager is null.");

        for (StateView subView : subViews) {
            subView.initGUIElements();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    protected GuiManager getGuiManager() {
        return guiManager;
    }

    @SuppressWarnings("unchecked")
    public void debugToLog() {
        log.info("View: " + toString());
        log.info("View Stage: " + mainStage.toString());
        for (Actor actor : mainStage.getActors()) {
            log.info(actor.toString() + ": " + actor.isVisible());
        }

        for (StateView subView : subViews) {
            ((AbstractStateView<T>) subView).debugToLog();
        }

    }

    @Override
    public void setMainStage(Stage stage) {
        Validate.isTrue(this.mainStage == null);
        this.mainStage = stage;

        if (getGuiManager() != null) {
            getGuiManager().setStage(stage);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void collectAllStages(Collection<Stage> result, boolean enabledViewsOnly) {
        if (isEnabled) {
            result.add(getMainStage());
        }
        for (StateView subView : getSubViews()) {
            subView.collectAllStages(result, enabledViewsOnly);
        }
    }

    @Override
    public void collectAllViews(Collection<StateView> result) {
        result.add(this);
        for (StateView subView : getSubViews()) {
            subView.collectAllViews(result);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Stage getScene() {
        return getMainStage();
    }

    @Override
    public Map<String, Object> getContext() {
        Map<String, Object> context = new HashMap<>();
        Validate.notNull(binding, "No binding is set for " + getClass().getCanonicalName());
        context.put("binding", binding);
        return context;
    }

    /*
     * public void addAnimation(PyramideAnimation animation) {
     * animationList.add(animation); }
     * 
     * public void addAnimationSafe(PyramideAnimation animation) {
     * animationAdditionList.add(animation); }
     */

    /**
     * These subviews automatically get enabled after this view is shown
     * 
     * @return
     */
    protected abstract Collection<Class<? extends AbstractStateSubView<?>>> getAutoEnabledSubViews();
}
