package net.kiberion.swampmachine.gui.composer.populators;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.kiberion.swampmachine.gui.annotations.Bound;
import net.kiberion.swampmachine.gui.annotations.ElementHints;
import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.elements.CompositionElement;
import net.kiberion.swampmachine.utils.MapUtils;

//Populates GDX scene from a composition 
public class GdxPopulator implements Populator {

    @Autowired
    private GdxElementFactory elementFactory;
    
    @Override
    public void populate(CompositionConsumer targetConsumer, Collection<Composition> sourceCompositions) {
        Map<String, Composition> compositionMap = MapUtils.toMap(sourceCompositions);

        BoundElementsExtractor elementsExtractor = new BoundElementsExtractor();
        Collection<Field> elementFields = elementsExtractor.extractElementFields(targetConsumer);

        for (Field field : elementFields) {
            Bound bindInfo = field.getAnnotation(Bound.class);
            ElementHints elementHints = field.getAnnotation(ElementHints.class);
            Composition composition = resolveCompositionForBoundElement(bindInfo, compositionMap);
            CompositionElement element = composition.getElementMap().get(bindInfo.id());
            
            field.setAccessible(true);
            try {
                field.set(targetConsumer, elementFactory.buildElement(composition, element, elementHints));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        
        //Stage stage = targetConsumer.getScene();
        //attachToStage (stage, null);
    }
    
    protected Composition resolveCompositionForBoundElement (Bound bindInfo, Map<String, Composition> compositionMap) {
        if (compositionMap.size() == 1) {
            return compositionMap.values().iterator().next();
        } else {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }
    
    protected void attachToStage (Stage stage, Actor actor) {
        stage.addActor(actor);
    }

}
