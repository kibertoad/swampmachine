package net.kiberion.gui.listeners.onclick;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * @author: kibertoad
 */
public class SetLabelListener extends ChangeListener {

    private final Label label;
    private final String text;

    public SetLabelListener(Label label, String text) {
        this.label = label;
        this.text = text;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        label.setText(text);
    }
}
