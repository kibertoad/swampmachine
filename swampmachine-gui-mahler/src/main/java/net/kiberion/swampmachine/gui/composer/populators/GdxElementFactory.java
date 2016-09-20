package net.kiberion.swampmachine.gui.composer.populators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;

import lombok.Getter;
import net.kiberion.swampmachine.gui.annotations.ElementHints;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.api.ParameterTransformer;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.composer.transformers.TransformerRegistry;
import net.kiberion.swampmachine.gui.elements.CompositionElement;
import net.kiberion.swampmachine.gui.elements.ElementPrototypeRegistry;

public class GdxElementFactory {

    @Autowired
    @Getter
    private ElementPrototypeRegistry elementRegistry;

    @Autowired
    @Getter
    private TransformerRegistry transformerRegistry;

    protected Object getTransformedProperty(CompositionElement sourceElement, String property) {
        ParameterTransformer transformer = transformerRegistry.getTransformers().get(property);
        Object value = sourceElement.getProperties().get(property);
        if (transformer != null) {
            value = transformer.transform(value);
        }
        return value;
    }

    protected Actor buildActor(ElementPrototype prototype, Class<?> clazz, List<Class<?>> constructorValueClasses,
            List<Object> constructorPropertiesToSet) {
        try {
            if (prototype.constructorProperties().length == 0) {
                return (Actor) clazz.newInstance();
            } else {
                Constructor<?> constructor = clazz
                        .getConstructor(constructorValueClasses.toArray(new Class<?>[constructorValueClasses.size()]));
                return (Actor) constructor.newInstance(constructorPropertiesToSet.toArray());
            }
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException
                | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public Actor buildElement(Composition composition, CompositionElement sourceElement, ElementHints elementHints) {
        Validate.notNull(sourceElement, "Composition element is null");
        Actor result = null;

        String elementType = sourceElement.getType();
        Validate.notNull(elementType, "No element prototype set for element: " + sourceElement.getId());
        Class<?> clazz = elementRegistry.getElementMap().get(elementType);
        Validate.notNull(clazz, "Unknown element prototype: " + elementType);
        ElementPrototype prototype = clazz.getAnnotation(ElementPrototype.class);

        List<Class<?>> constructorValueClasses = new ArrayList<>();
        List<Object> constructorPropertiesToSet = new ArrayList<>();
        Map<String, Object> propertiesToSet = new HashMap<>();
        Set<String> resolvedProperties = new HashSet<>();

        for (String constructorProperty : prototype.constructorProperties()) {
            Object value = getTransformedProperty(sourceElement, constructorProperty);
            constructorPropertiesToSet.add(value);
            constructorValueClasses.add(value.getClass());
            resolvedProperties.add(constructorProperty);
        }

        for (String property : prototype.supportedProperties()) {
            if (resolvedProperties.contains(property)) {
                continue;
            }
            Object value = getTransformedProperty(sourceElement, property);
            propertiesToSet.put(property, value);
        }

        result = buildActor(prototype, clazz, constructorValueClasses, constructorPropertiesToSet);
        
        Validate.notNull(sourceElement.getPosition(), "Position not defined for element: "+sourceElement.getId());
        result.setPosition(sourceElement.getPosition().getX(), sourceElement.getPosition().getY());

        PropertyAccessor targetAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);
        for (Entry<String, Object> entry : propertiesToSet.entrySet()) {
            targetAccessor.setPropertyValue(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
