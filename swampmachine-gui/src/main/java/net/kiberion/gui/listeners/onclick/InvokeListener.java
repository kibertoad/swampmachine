package net.kiberion.gui.listeners.onclick;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import net.kiberion.entities.common.api.Invokable;


/**
 * @author kibertoad
 */
public class InvokeListener extends ChangeListener {

    private final Invokable trigger;

    public InvokeListener(Invokable setTrigger) {
        this.trigger = setTrigger;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        trigger.invoke();
    }
}
