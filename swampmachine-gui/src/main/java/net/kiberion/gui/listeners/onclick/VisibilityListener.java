/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.gui.listeners.onclick;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 *
 * @author kibertoad
 */
public class VisibilityListener extends ChangeListener {

    public Actor effectActor;
    public Boolean sign = null;

    /**
     * Toggle visibility of actor
     */
    public VisibilityListener(Actor actor) {
        this.effectActor = actor;
    }

    /**
     * Set visibility of actor
     */
    public VisibilityListener(Actor actor, Boolean toWhat) {
        this.effectActor = actor;

        if (toWhat != null) {
            sign = toWhat;
        }
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if (sign != null) {
            effectActor.setVisible(sign);
        } else {
            effectActor.setVisible(!(effectActor.isVisible()));
        }

    }
}
