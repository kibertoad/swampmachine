package net.kiberion.swampmachine.gui.listeners.onhover;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.kiberion.swampmachine.api.invokables.Invokable;


/**
 * @author kibertoad
 */
public class InvokeOnHoverListener extends InputListener {

    private final Invokable model;

    public InvokeOnHoverListener(Invokable model) {
        this.model = model;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        model.invoke();
    }
}