package net.kiberion.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GenericInputAdapter extends InputAdapter{

    public static void exitGame() {
        //GameplayCalculator.instance().logGameplayTime();
        Gdx.app.exit();
    }

    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F10) {
            exitGame();
            return true;
        }

        if ((keycode == Input.Keys.F12) || (keycode == Input.Keys.ESCAPE)) {
            Gdx.graphics.setDisplayMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), !(Gdx.graphics.isFullscreen()));
            return true;
        }

        return false;
    }    
    
    
}
