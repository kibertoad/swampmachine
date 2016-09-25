package net.kiberion.swampmachine.gui.composer.populators;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        Set<CompositionElement> processedElements = new HashSet<>();

        //Create and attach bound elements
        for (Field field : elementFields) {
            Bound bindInfo = field.getAnnotation(Bound.class);
            ElementHints elementHints = field.getAnnotation(ElementHints.class);
            Composition composition = resolveCompositionForBoundElement(bindInfo, compositionMap);
            CompositionElement element = composition.getElementMap().get(bindInfo.id());
            Validate.notNull(element,
                    "Bound element " + bindInfo.id() + " not found in composition " + composition.getId());

            Actor builtElement = buildElement(composition, element, elementHints, targetConsumer.getContext());
            injectElement(targetConsumer, field, builtElement);

            processedElements.add(element);
        }

        //Create and attach unbound elements
        for (Composition composition : sourceCompositions) {
            for (CompositionElement element : composition.getElementMap().values()) {
                if (processedElements.contains(element)) {
                    continue;
                }

                Actor builtElement = buildElement(composition, element, null, targetConsumer.getContext());
                attachToStage(targetConsumer, builtElement);
                processedElements.add(element);
            }
        }
    }

    protected Actor buildElement(Composition composition, CompositionElement element, ElementHints elementHints, Map<String, Object> context) {
        return elementFactory.buildElement(composition, element, elementHints, context);
    }

    protected void injectElement(CompositionConsumer targetConsumer, Field field, Actor builtElement) {
        field.setAccessible(true);
        try {
            field.set(targetConsumer, builtElement);
            attachToStage(targetConsumer, builtElement);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    protected Composition resolveCompositionForBoundElement(Bound bindInfo, Map<String, Composition> compositionMap) {
        if (compositionMap.size() == 1) {
            return compositionMap.values().iterator().next();
        } else {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    protected void attachToStage(CompositionConsumer targetConsumer, Actor actor) {
        Stage stage = targetConsumer.getScene();
        stage.addActor(actor);
    }

}
