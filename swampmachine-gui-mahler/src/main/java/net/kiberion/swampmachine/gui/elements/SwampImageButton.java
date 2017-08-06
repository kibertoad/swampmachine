package net.kiberion.swampmachine.gui.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.gui.annotations.ElementBlueprint;
import net.kiberion.swampmachine.invokables.ScriptInvokable;

@ElementBlueprint(id = "swImageButton")
public class SwampImageButton extends ImageButton {

    public TextureRegion originalImage;
    public Pixmap picture;
    public boolean nonClickableTransparency = true;

    private static final Logger log = LogManager.getLogger();

    private List<Invokable> invokeEffects = new ArrayList<>();

    public SwampImageButton(TextureRegion region, Pixmap picture) {
        super(new TextureRegionDrawable(region));
        this.picture = picture;
        this.originalImage = region;
    }

    /**
     * In order to make button generate events, invokable with event payload
     * gets added
     * 
     * @param invokableEffect
     * @return
     */
    @NodeId(ids = {"onClickEvent", "onClickSound"})
    @InjectTransformedProperty
    public SwampImageButton addInvokable(Invokable invokableEffect) {
        Validate.notNull(invokableEffect);
        invokeEffects.add(invokableEffect);
        return this;
    }

    @NodeId(ids = {"onClickScript"})
    @InjectTransformedProperty
    public SwampImageButton addOnClickScript(ScriptInvokable onClickScript) {
        addInvokable(onClickScript);
        return this;
    }

    @NodeId(ids = {"onClickListener"})
    @InjectTransformedProperty
    @Override
    public boolean addListener(EventListener listener) {
        return super.addListener(listener);
    }

    public void clearUserListeners() {
        clearListeners();
        invokeEffects.clear();
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor result;
        result = super.hit(x, y, touchable);

        if (result == null) {
            return null;
        }

        if ((!isTransparent((int) x, (int) y))) {
            return result;
        } else
            return null;
    }

    public boolean isTransparent(int x, int y) {
        if (!nonClickableTransparency) {
            return false;
        }

        if (picture != null) {
            y = (int) (this.getHeight() - y);
            int pixel = picture.getPixel(x, y);
            pixel &= 0x000000ff;
            return pixel == 0;
        }
        return false;
    }

    public void setPosition(Position position) {
        setPosition(position.getX(), position.getY());
    }

    @Override
    public boolean fire(Event event) {
        if (event instanceof ChangeListener.ChangeEvent) {
            for (Invokable invokeEffect_ : invokeEffects) {
                invokeEffect_.invoke();
            }
            return super.fire(event);
        }
        else {
            return super.fire(event);
        }
    }
}
