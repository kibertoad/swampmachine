package net.kiberion.swampmachine.gui.listeners.onclick;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import net.kiberion.swampmachine.api.elements.Selectable;
import net.kiberion.swampmachine.api.invokables.Invokable;

/**
 * @author: kibertoad
 */
public class ToggleSelectionListener extends ChangeListener {

    private static final Logger log = LogManager.getLogger();
    
    private final Selectable selectable;

    public ToggleSelectionListener(Selectable selectable) {
        this.selectable = selectable;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if (selectable.isSelected()) {
            for (Invokable effect : selectable.getOnDeselectEffects()) {
                effect.invoke();
            }
        } else {
            for (Invokable effect : selectable.getOnSelectEffects()) {
                effect.invoke();
            }
        }
        log.info ("Toggled selection");
    }
}
