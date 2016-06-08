package net.kiberion.swampmachine.gui.view;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;

import lombok.Getter;
import net.kiberion.entities.common.api.Recalculable;
import net.kiberion.swampmachine.factories.InvokablesFactory;
import net.kiberion.swampmachine.gui.managers.GuiManager;

public abstract class AbstractStateView<T> extends Container<Actor> implements StateView, Recalculable, InitializingBean{

    private static final Logger log = LogManager.getLogger();
    
    @Getter
    private Stage stage;

    @Getter
    private T model;

    @Autowired
    private GuiManager guiManager;
    
    @Autowired
    @Getter
    private InvokablesFactory invokablesFactory;
    
    @Autowired
    public void setModel (T model) {
        this.model = model;
    }
    
    @Override
    public void show() {
        setVisible(true);
    }
    
    @Override
    public void hide() {
        setVisible(false);
    }

    @Override
    public void act(float delta) {
        /*
        for (PyramideAnimation animation : animationList) {
            animation.act(delta);

            if (animation.isCompleted() && (!animation.isLooped())) {
                animationGarbageList.add(animation);
            }
        }

        while (!animationGarbageList.isEmpty()) {
            animationList.remove(animationGarbageList.poll());
        }

        while (!animationAdditionList.isEmpty()) {
            animationList.remove(animationAdditionList.poll());
        }
        */

    }
  
    @Override
    public void initGUIElements () {
        Validate.notNull(getStage(), "Stage is null.");
        Validate.notNull(getGuiManager(), "GUIManager is null.");
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

    protected GuiManager getGuiManager() {
        return guiManager;
    }
    
    @Override
    public void setStage(Stage stage) {
        Validate.notNull(getGuiManager(), "GUIMAnager is null.");
        Validate.isTrue(getStage() == null, "Stage was already set.");
        super.setStage(stage);
        this.stage = stage;
        getGuiManager().setStage(stage);
    }
    
    public void debugToLog () {
        log.info("View Stage: "+getStage().toString());
        for (Actor actor: getStage().getActors()) {
            log.info(actor.toString()+ ": "+actor.isVisible());
        }
        
    }
    
    @Override
    public void update() {
    }
    
    /*
    public void addAnimation(PyramideAnimation animation) {
        animationList.add(animation);
    }

    public void addAnimationSafe(PyramideAnimation animation) {
        animationAdditionList.add(animation);
    }
    */  
}
