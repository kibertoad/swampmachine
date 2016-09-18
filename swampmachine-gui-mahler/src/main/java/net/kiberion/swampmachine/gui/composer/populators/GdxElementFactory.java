package net.kiberion.swampmachine.gui.composer.populators;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;

import lombok.Getter;
import net.kiberion.swampmachine.gui.annotations.ElementHints;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.elements.CompositionElement;
import net.kiberion.swampmachine.gui.elements.ElementPrototypeRegistry;

public class GdxElementFactory {

    @Autowired
    @Getter
    private ElementPrototypeRegistry elementRegistry;

    public Actor buildElement(Composition composition, CompositionElement sourceElement, ElementHints elementHints) {
        Validate.notNull(sourceElement, "Composition element is null");
        Actor result = null;

        String elementType = sourceElement.getType();
        Validate.notNull(elementType, "No element prototype set for element: " + sourceElement.getId());
        Class<?> clazz = elementRegistry.getElementMap().get(elementType);
        Validate.notNull(clazz, "Unknown element prototype: " + elementType);
        try {
            result = (Actor) clazz.newInstance();

            ElementPrototype prototype = clazz.getAnnotation(ElementPrototype.class);
            PropertyAccessor targetAccessor = PropertyAccessorFactory.forDirectFieldAccess(result);
            for (String property : prototype.supportedProperties()) {
                Object value = sourceElement.getProperties().get(property);
                targetAccessor.setPropertyValue(property, value);
            }
            
            result.setPosition(sourceElement.getPosition().getX(), sourceElement.getPosition().getY());

        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }

        return result;
    }

}
