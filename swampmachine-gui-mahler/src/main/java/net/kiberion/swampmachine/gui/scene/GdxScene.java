package net.kiberion.swampmachine.gui.scene;

import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;
import net.kiberion.swampmachine.gui.api.Scene;

public class GdxScene implements Scene<Stage>{

    @Getter
    private final Stage scene;

    public GdxScene(Stage scene) {
        this.scene = scene;
    }
    
    
}
