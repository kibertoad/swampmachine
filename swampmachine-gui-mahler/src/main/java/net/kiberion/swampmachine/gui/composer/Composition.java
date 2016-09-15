package net.kiberion.swampmachine.gui.composer;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.AbstractModelEntityDescriptor;
import net.kiberion.swampmachine.gui.elements.CompositionElement;

public class Composition extends AbstractModelEntityDescriptor{

    @Getter
    private final List <CompositionElement> elements  = new ArrayList<>();
    
}
