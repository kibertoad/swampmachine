package net.kiberion.swampmachine.gui.composer.populators;

import com.badlogic.gdx.scenes.scene2d.Stage;

import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.scene.GdxScene;

//Populates GDX scene from a composition 
public class GdxPopulator implements Populator{

    @Override
    public void populate(CompositionConsumer targetConsumer, Composition sourceComposition) {
        GdxScene scene = (GdxScene) targetConsumer.getScene();
        Stage stage = scene.getScene();
        
    }

}
