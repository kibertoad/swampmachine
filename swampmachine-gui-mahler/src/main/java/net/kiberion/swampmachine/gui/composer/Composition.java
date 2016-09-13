package net.kiberion.swampmachine.gui.composer;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import net.kiberion.swampmachine.gui.elements.CompositionElement;

public class Composition {

    @Getter
    private String id;
    
    @Getter
    private final List <CompositionElement> elements  = new ArrayList<>();
    
}
