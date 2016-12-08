package net.kiberion.swampmachine.gui.elements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.composer.transformers.ScriptTransformer;
import net.kiberion.swampmachine.gui.observers.LabelUpdatingObserver;
import net.kiberion.swampmachine.invokables.ScriptInvokable;
import net.kiberion.swampmachine.styling.StyleFactory;
import net.kiberion.swampmachine.subscription.AbstractObservable;

/**
 * Label using default skin
 *
 * @author kibertoad
 */

@ElementPrototype(id = "swLabel", supportedProperties = {"text"})
@ElementTransformedProperty(sourceProperty = "labelValue", targetTransformer = ScriptTransformer.class)
public class SwampLabel extends Label implements net.kiberion.swampmachine.api.elements.Label {

    private BitmapFont font;

    public SwampLabel() {
        super("", UiManager.instance().getDefaultSkin());
    }
    
    public SwampLabel(CharSequence text, int setX, int setY, LabelStyle style) {
        super(text, style);

        this.setPosition(setX, setY);
    }
    
    @NodeId (ids = {"labelValue"})
    @InjectTransformedProperty
    public void setText(ScriptInvokable script) {
        AbstractObservable<?, ?> observable = script.invoke();
        setText (observable.getValue().toString());
        observable.addObserver(new LabelUpdatingObserver (this));
    }    

    public SwampLabel(AbstractObservable<?, ?> observable) {
        this(observable.getValue().toString());
        observable.addObserver(new LabelUpdatingObserver (this));
    }
    
    public SwampLabel(CharSequence text) {
        super(text, UiManager.instance().getDefaultSkin());
    }

    public SwampLabel(CharSequence text, float setX, float setY) {
        super(text, UiManager.instance().getDefaultSkin());

        this.setPosition(setX, setY);
    }

    public SwampLabel(String setText, LabelStyle setStyle) {
        super(setText, setStyle);
    }

    public void setFont(StyleFactory generator, String fontName, int size) {
        getStyle().font = generator.getFont(fontName, size);
    }

    public SwampLabel(int text) {
        super(Integer.toString(text), UiManager.instance().getDefaultSkin());
    }

    public void setText(int newText) {
        super.setText(Integer.toString(newText));    //To change body of overridden methods use File | Settings | File Templates.
    }
    
    public void setPosition (int[] coords) {
        setPosition (coords[0], coords[1]);
    }
    
    public void setPosition (Position coords) {
        setPosition (coords.getX(), coords.getY());
    }
    

    public void setFont() {
        getStyle().font = new BitmapFont();
    }

    public void setMaxWidth(int width) {
        setWrap(true);
        setWidth(width);
    }
    
    public void addActionOnClick (Invokable invokeAction) {
        addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                invokeAction.invoke();
                return true;
            }
        });        
    }

    public void setTouchable(boolean toValue) {
        if (toValue) {
            this.setTouchable(Touchable.enabled);
        } else {
            this.setTouchable(Touchable.disabled);
        }
    }
    
    @Override
    public String toString() {
        return super.toString()+" x:"+getX()+" y:"+getY();
    }
}
