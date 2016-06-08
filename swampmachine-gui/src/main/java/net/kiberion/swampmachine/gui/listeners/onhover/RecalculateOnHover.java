package net.kiberion.swampmachine.gui.listeners.onhover;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.kiberion.entities.common.api.Recalculable;


/**
 * @author: kibertoad
 */
public class RecalculateOnHover extends InputListener {

    private final Recalculable model;

    public RecalculateOnHover(Recalculable model) {
        this.model = model;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        model.update();
    }
}
