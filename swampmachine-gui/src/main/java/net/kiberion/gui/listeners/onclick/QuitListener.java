package net.kiberion.gui.listeners.onclick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * @author kibertoad
 */
public class QuitListener extends ChangeListener {

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        Gdx.app.exit();
    }
}
