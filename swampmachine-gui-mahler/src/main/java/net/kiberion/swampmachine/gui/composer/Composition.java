package net.kiberion.swampmachine.gui.composer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.AbstractModelEntityDescriptor;
import net.kiberion.swampmachine.gui.composition.elements.CompositionElement;
import net.kiberion.swampmachine.utils.MapUtils;

public class Composition extends AbstractModelEntityDescriptor {

    @Getter
    private Map<String, CompositionElement> elementMap = new HashMap<>();

    public void setElements(Collection<CompositionElement> elements) {
        elementMap = MapUtils.toMap(elements);
    }
}
