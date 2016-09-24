package net.kiberion.swampmachine.gui.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.kiberion.entities.common.api.Invokable;
import net.kiberion.entities.common.api.Toggleable;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.gui.listenerconditions.ListenerCondition;
import net.kiberion.swampmachine.gui.listenerconditions.ListenerConditionContainer;

@ElementPrototype(id = "swTextButton")
public class SwampTextButton <t extends MetadataHolderBlock> extends TextButton implements Toggleable {

    private ListenerConditionContainer changeConditions = new ListenerConditionContainer();
    private List<Invokable> invokeEffects = new ArrayList<>();
    private TextButton.TextButtonStyle styleOnHover;
    private TextButton.TextButtonStyle styleUsual;
    
    public t linkedNode = null;

    public SwampTextButton(String text) {
        super(text, UiManager.instance().getDefaultSkin());
    }

    public SwampTextButton<t> addListenerChain(ChangeListener listener) {
        addListener(listener);
        return this;
    }

    public SwampTextButton (String text, Invokable invokeEffect) {
        super(text, UiManager.instance().getDefaultSkin());
        this.invokeEffects.add(invokeEffect);
    }

    public SwampTextButton(String text, t setNode) {
        super(text, UiManager.instance().getDefaultSkin());
        linkedNode = setNode;
    }
    
    public SwampTextButton(String setText, int setX, int setY) {
        this(setText);
        setPosition(setX, setY);
    }
    

    @NodeId (id = "onClickEvent")
    @InjectTransformedProperty
    public SwampTextButton<t> addInvokable(Invokable invokableEffect) {
        invokeEffects.add(invokableEffect);
        return this;
    }
    
    @NodeId (id = "onClickListener")
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

    public void addCondition(ListenerCondition condition) {
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

    /*
    @Override
    public void update() {
    }
    */

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
