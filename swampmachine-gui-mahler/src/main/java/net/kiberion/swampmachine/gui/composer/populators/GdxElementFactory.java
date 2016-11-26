package net.kiberion.swampmachine.gui.composer.populators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.gui.annotations.ElementHints;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.InjectProperty;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.composer.transformers.TransformerHelper;
import net.kiberion.swampmachine.gui.composition.elements.CompositionElement;
import net.kiberion.swampmachine.gui.composition.elements.ElementPrototypeRegistry;
import net.kiberion.swampmachine.gui.utils.InjectionUtils;
import net.kiberion.swampmachine.utils.common.ReflectionUtils;

public class GdxElementFactory {

    @Autowired
    @Getter
    private ElementPrototypeRegistry elementRegistry;

    @Autowired
    @Getter
    private TransformerHelper transformer;

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

    @SuppressWarnings("unchecked")
    public Actor buildElement(Composition composition, CompositionElement sourceElement, ElementHints elementHints, Map<String, Object> context) {
        Validate.notNull(sourceElement, "Composition element is null");
        Actor result = null;

        String elementType = sourceElement.getType();
        Validate.notNull(elementType, "No element prototype set for element: " + sourceElement.getId());
        Class<?> clazz = elementRegistry.getElementMap().get(elementType);
        Validate.notNull(clazz, "Unknown element prototype: " + elementType);
        ElementPrototype prototype = clazz.getAnnotation(ElementPrototype.class);

        List<Method> injectionMethods = ReflectionUtils.getSupportedMethodsWithAnnotation(clazz, InjectProperty.class, sourceElement.getProperties().keySet());
        List<Method> transformedInjectionMethods = ReflectionUtils.getSupportedMethodsWithAnnotation(clazz, InjectTransformedProperty.class, sourceElement.getProperties().keySet());
        
        
        List<Class<?>> constructorValueClasses = new ArrayList<>();
        List<Object> constructorPropertiesToSet = new ArrayList<>();
        Map<String, Object> propertiesToSet = new HashMap<>();
        Set<String> resolvedProperties = new HashSet<>();

        for (String constructorProperty : prototype.constructorProperties()) {
            Object value = transformer.getTransformedProperty(sourceElement.getProperties(), constructorProperty, context);
            constructorPropertiesToSet.add(value);
            constructorValueClasses.add(value.getClass());
            resolvedProperties.add(constructorProperty);
        }

        for (String property : prototype.supportedProperties()) {
            if (resolvedProperties.contains(property)) {
                continue;
            }
            Object value = transformer.getTransformedProperty(sourceElement.getProperties(), property, context);
            propertiesToSet.put(property, value);
        }
        
        result = buildActor(prototype, clazz, constructorValueClasses, constructorPropertiesToSet);
        
        Validate.notNull(sourceElement.getPosition(), "Position not defined for element: "+sourceElement.getId());
        result.setPosition(sourceElement.getPosition().getX(), sourceElement.getPosition().getY());

        PropertyAccessor targetAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);
        for (Entry<String, Object> entry : propertiesToSet.entrySet()) {
            targetAccessor.setPropertyValue(entry.getKey(), entry.getValue());
        }


        for (Method injectionMethod : injectionMethods) {
            InjectProperty metadata = injectionMethod.getAnnotation(InjectProperty.class);
            
            for (String injectionProperty : metadata.methodProperties()) {
                List<Map<String, Object>> entries = (List<Map<String, Object>>) sourceElement.getProperties().get(metadata.id());
                for (Map<String, Object> props : entries ) {
                    List<Object> args = new ArrayList<>();
                    args.add(props.get(injectionProperty));
                    try {
                        injectionMethod.invoke(result, args.toArray());
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        throw new IllegalStateException (e);
                    }
                }
            }
        }
        
        InjectionUtils.injectTransformedValues(result, transformedInjectionMethods, sourceElement.getProperties(), transformer, context);
        
        return result;
    }

}
