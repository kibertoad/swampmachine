package net.kiberion.swampmachine.gui.listeners.onclick;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import net.kiberion.entities.common.api.Recalculable;

/**
 * @author kibertoad
 */
public class RecalculateOnClick extends ChangeListener {

    private final Recalculable model;

    public RecalculateOnClick(Recalculable model) {
        this.model = model;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        model.update();
    }
}
