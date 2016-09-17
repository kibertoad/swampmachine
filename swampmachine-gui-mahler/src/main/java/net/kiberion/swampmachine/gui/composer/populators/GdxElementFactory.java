package net.kiberion.swampmachine.gui.composer.populators;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;

import lombok.Getter;
import net.kiberion.swampmachine.gui.annotations.ElementHints;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.elements.CompositionElement;
import net.kiberion.swampmachine.gui.elements.ElementPrototypeRegistry;

public class GdxElementFactory {

    @Autowired
    @Getter
    private ElementPrototypeRegistry elementRegistry;

    public Actor buildElement(Composition composition, CompositionElement element, ElementHints elementHints) {
        Actor result = null;

        String elementType = element.getType();
        Validate.notNull(elementType, "No element prototype set for element: " + element.getId());
        Class<?> clazz = elementRegistry.getElementMap().get(elementType);
        Validate.notNull(clazz, "Unknown element prototype: " + elementType);
        try {
            result = (Actor) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }

        return result;
    }

}
