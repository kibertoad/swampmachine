package net.kiberion.swampmachine.gui.composer.populators;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.kiberion.swampmachine.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.annotations.Bound;
import net.kiberion.swampmachine.gui.annotations.ElementHints;
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
            Validate.notNull (element, "Bound element "+bindInfo.id()+" not found in composition "+composition.getId());
            
            field.setAccessible(true);
            try {
                Actor builtElement = elementFactory.buildElement(composition, element, elementHints);
                field.set(targetConsumer, builtElement);
                Stage stage = targetConsumer.getScene();
                attachToStage (stage, builtElement);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        
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
