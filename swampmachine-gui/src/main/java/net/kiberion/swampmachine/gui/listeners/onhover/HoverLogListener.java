package net.kiberion.swampmachine.gui.listeners.onhover;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * @author: kibertoad
 */
public class HoverLogListener extends InputListener {

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);

        Gdx.app.log("Info", "This stuff just got hovered");
    }
}
