package net.kiberion.swampmachine.api.elements;

import java.util.List;

import net.kiberion.swampmachine.api.invokables.Invokable;

public interface Selectable {

    public List<Invokable> getOnSelectEffects ();
    public List<Invokable> getOnDeselectEffects ();
    
    public boolean isCanSelect();
    public boolean isCanDeselect();
    
    public boolean isSelected();
    
}
