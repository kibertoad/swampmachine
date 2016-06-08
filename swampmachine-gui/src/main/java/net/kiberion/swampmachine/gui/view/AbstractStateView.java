package net.kiberion.swampmachine.gui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;
import net.kiberion.entities.common.api.Recalculable;
import net.kiberion.swampmachine.factories.InvokablesFactory;
import net.kiberion.swampmachine.gui.managers.GuiManager;

public abstract class AbstractStateView<T> implements StateView, Recalculable, InitializingBean {

    private static final Logger log = LogManager.getLogger();

    @Getter
    private final Stage stage;

    @Getter
    private T model;

    private GuiManager guiManager;

    @Autowired
    @Getter
    private InvokablesFactory invokablesFactory;

    @Getter
    private List<StateView> subViews = new ArrayList<>();

    private boolean isEnabled = true;
    
    
    @Override
    public void addSubView(StateView view) {
        Validate.notNull(view);
        subViews.add(view);
    }

    public AbstractStateView() {
        stage = new Stage();
    }
    
    @Autowired
    public void setGuiManager(GuiManager guiManager) {
        this.guiManager = guiManager;
        Validate.notNull(getStage());
        this.guiManager.setStage(getStage());
    }

    @Autowired
    public void setModel(T model) {
        this.model = model;
    }

    @Override
    public void hide() {
        isEnabled = false;
    }

    @Override
    public void show() {
        isEnabled = true;
    }

    @Override
    public void render() {
        if (isEnabled) {
            stage.draw();

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
        Validate.notNull(getStage(), "Stage is null.");
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

    public void debugToLog() {
        log.info("View: " + toString());
        log.info("View Stage: " + getStage().toString());
        for (Actor actor : getStage().getActors()) {
            log.info(actor.toString() + ": " + actor.isVisible());
        }
        
        for (StateView subView : subViews) {
            ((AbstractStateView<T>) subView).debugToLog();
        }

    }

    @Override
    public void update() {
    }

    /*
     * public void addAnimation(PyramideAnimation animation) {
     * animationList.add(animation); }
     * 
     * public void addAnimationSafe(PyramideAnimation animation) {
     * animationAdditionList.add(animation); }
     */
}
