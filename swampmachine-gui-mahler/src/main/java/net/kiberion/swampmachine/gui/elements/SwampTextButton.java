package net.kiberion.swampmachine.gui.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.api.common.Condition;
import net.kiberion.swampmachine.api.common.Toggleable;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.annotations.ElementBlueprint;
import net.kiberion.swampmachine.gui.listenerconditions.ListenerConditionContainer;
import net.kiberion.swampmachine.invokables.ScriptInvokable;

@ElementBlueprint(id = "swTextButton", constructorProperties="text")
public class SwampTextButton <T extends MetadataHolderBlock> extends TextButton implements Toggleable {

    private ListenerConditionContainer changeConditions = new ListenerConditionContainer();
    private List<Invokable> invokeEffects = new ArrayList<>();
    private TextButton.TextButtonStyle styleOnHover;
    private TextButton.TextButtonStyle styleUsual;
    
    public T linkedNode = null;

    public SwampTextButton(String text) {
        super(text, UiManager.instance().getDefaultSkin());
    }

    public SwampTextButton<T> addListenerChain(ChangeListener listener) {
        Validate.notNull(listener);
        addListener(listener);
        return this;
    }

    public SwampTextButton (String text, Invokable invokeEffect) {
        super(text, UiManager.instance().getDefaultSkin());
        addInvokable (invokeEffect);
    }

    public SwampTextButton(String text, T setNode) {
        super(text, UiManager.instance().getDefaultSkin());
        linkedNode = setNode;
    }
    
    public SwampTextButton(String setText, int setX, int setY) {
        this(setText);
        setPosition(setX, setY);
    }

    public SwampTextButton(ButtonEntry buttonEntry) {
        this (buttonEntry.getText());
        addInvokable(buttonEntry.getOnClickEffect());
    }

    public void setPosition(Position position) {
        setPosition(position.getX(), position.getY());
    }
    

    /**
     * In order to make button generate events, invokable with event payload gets added
     * @param invokableEffect
     * @return
     */
    @NodeId (ids = {"onClickEvent", "onClickSound"})
    @InjectTransformedProperty
    public SwampTextButton<T> addInvokable(Invokable invokableEffect) {
        Validate.notNull(invokableEffect, "No onClick effect is set for this button.");
        invokeEffects.add(invokableEffect);
        return this;
    }

    @NodeId (ids = {"onClickScript"})
    @InjectTransformedProperty
    public SwampTextButton<T> addOnClickScript(ScriptInvokable onClickScript) {
        addInvokable (onClickScript);
        return this;
    }
    
    @NodeId (ids = {"onClickListener"})
    @InjectTransformedProperty
    @Override
    public boolean addListener(EventListener listener) {
        return super.addListener(listener);
    }
    
    public void clearUserListeners() {
        ClickListener listener = getClickListener();
        clearListeners();
        invokeEffects.clear();
        addListener(listener);
    }

    public void addCondition(Condition condition) {
        changeConditions.add(condition);
    }

    @Override
    public boolean fire(Event event) {
        if (event instanceof ChangeListener.ChangeEvent) {
            if (changeConditions.isSatisfied()) {

                for (Invokable invokeEffect_: invokeEffects) {
                    invokeEffect_.invoke();
                }
                
                return super.fire(event);
            } else return false;
        } 
        
        else {
            return super.fire(event);
        }
    }

    @Override
    public void setText(String setText) {
        super.setText(setText);
        super.pack();
    }

    /*
    public void addHideView(final StateView view) {
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                view.hide();
            }
        });
    }
    */

    public void addHideWindowEffect(final Window window, final int swooshToX, final int swooshToY) {
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                window.setModal(false);
                window.addAction(
                        Actions.sequence(Actions.fadeOut(.4f), Actions.moveTo(swooshToX, swooshToY)));
            }
        });
    }

    @Override
    public void enable() {
        setVisible(true);
    }

    @Override
    public void disable() {
        setVisible(false);
    }

    @Override
    public boolean isEnabled() {
        return (isVisible());
    }

    public void clearConditions() {
        changeConditions.clear();
    }

    public void setStyleOnHover(TextButtonStyle buttonStyle) {
        styleOnHover = buttonStyle;
        styleUsual = getStyle();
        
        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                setStyle(styleOnHover);
                super.enter(event, x, y, pointer, fromActor);
            }
            
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                setStyle(styleUsual);
                super.exit(event, x, y, pointer, toActor);
            }
        });        
        
    }

}
