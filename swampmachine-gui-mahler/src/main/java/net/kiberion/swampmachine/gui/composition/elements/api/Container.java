package net.kiberion.swampmachine.gui.composition.elements.api;

import java.lang.ref.WeakReference;
import java.util.List;

import net.kiberion.swampmachine.gui.composition.elements.CompositionElement;

public interface Container<T extends CompositionElement> {

    public List<WeakReference<T>> getChildren();
    
}
