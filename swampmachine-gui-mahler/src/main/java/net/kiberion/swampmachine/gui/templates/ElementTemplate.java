package net.kiberion.swampmachine.gui.templates;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.AbstractModelEntityDescriptor;
import net.kiberion.swampmachine.gui.composition.elements.CompositionElement;

public class ElementTemplate extends AbstractModelEntityDescriptor {

    @Getter
    private final Set<String> mandatoryParameters = new HashSet<>();    
    
    @Getter
    private final Set<String> optionalParameters = new HashSet<>();

    @Getter
    @Setter
    private CompositionElement template;
    
}
