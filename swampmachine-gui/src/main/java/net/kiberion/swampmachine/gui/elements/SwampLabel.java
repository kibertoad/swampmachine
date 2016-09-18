package net.kiberion.swampmachine.gui.elements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import net.kiberion.entities.common.api.Invokable;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.styling.StyleFactory;

/**
 * Label using default skin
 *
 * @author kibertoad
 */

@ElementPrototype(id = "swLabel", supportedProperties = {"text", "position"})
public class SwampLabel extends Label {

    private BitmapFont font;

    public SwampLabel() {
        super("", UiManager.instance().getDefaultSkin());
    }
    
    public SwampLabel(CharSequence text, int setX, int setY, LabelStyle style) {
        super(text, style);

        this.setPosition(setX, setY);
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
