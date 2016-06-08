package net.kiberion.swampmachine.gui.listeners.onhover;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * @author kibertoad
 */
public class DisplayActorOnHoverListener extends InputListener {

    private final Actor image;

    public DisplayActorOnHoverListener(Actor image) {
        this.image = image;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        image.setVisible(true);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        image.setVisible(false);
    }
}
