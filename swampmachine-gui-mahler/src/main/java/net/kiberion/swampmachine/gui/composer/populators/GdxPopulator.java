package net.kiberion.swampmachine.gui.composer.populators;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Stage;

import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.scene.GdxScene;
import net.kiberion.swampmachine.utils.MapUtils;

//Populates GDX scene from a composition 
public class GdxPopulator implements Populator {

    @Override
    public void populate(CompositionConsumer targetConsumer, Collection<Composition> sourceCompositions) {
        Map<String, Composition> compositionMap = MapUtils.toMap(sourceCompositions);

        BoundElementsExtractor elementsExtractor = new BoundElementsExtractor();
        Collection<Field> elementFields = elementsExtractor.extractElementFields(targetConsumer);

        for (Field field : elementFields) {
            field.setAccessible(true);
            try {
                field.set(targetConsumer, "dummy");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        GdxScene scene = (GdxScene) targetConsumer.getScene();
        Stage stage = scene.getScene();

    }

}
