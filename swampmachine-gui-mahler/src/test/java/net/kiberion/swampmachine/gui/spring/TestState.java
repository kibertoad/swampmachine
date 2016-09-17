package net.kiberion.swampmachine.gui.spring;

import lombok.Getter;
import net.kiberion.swampmachine.gui.annotations.Bound;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.api.Scene;
import net.kiberion.swampmachine.gui.scene.GdxScene;

@BoundCompositions(compositions = { "test" })
public class TestState implements CompositionConsumer{

    @Bound(id = "button")
    @Getter
    private String element;
    
    private Scene scene = new GdxScene(null);
    
    
    @SuppressWarnings("unchecked")
    @Override
    public Scene getScene() {
        return scene;
    }

}
