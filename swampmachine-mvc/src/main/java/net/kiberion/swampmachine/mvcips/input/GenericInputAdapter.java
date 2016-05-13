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
            // Display display = new Display();

            // Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
            // !(Gdx.graphics.isFullscreen()
            // DisplayMode display = new DisplayMode(Gdx.graphics.getWidth(),
            // Gdx.graphics.getHeight(), 60, 32);
            // Gdx.graphics.setFullscreenMode(displayMode);
            Gdx.graphics.setVSync(true);

            if (Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            } else {
                Gdx.graphics.setWindowedMode(1024, 768);
            }

            return true;
        }

        return false;
    }

}
