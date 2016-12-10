package net.kiberion.swampmachine.mvcips.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GenericInputAdapter extends InputAdapter {

    public static void exitGame() {
        Gdx.app.exit();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F10) {
            exitGame();
            return true;
        }

        if ((keycode == Input.Keys.F12) || (keycode == Input.Keys.ESCAPE)) {
            if (!Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            } else {
                Gdx.graphics.setWindowedMode(1280, 720);
            }
            Gdx.graphics.setVSync(true);

            return true;
        }

        return false;
    }

}
