package net.kiberion.swampmachine.api.elements;

import net.kiberion.swampmachine.api.invokables.Invokable;

public interface ButtonEntry {

    public boolean isActive ();
    public String getText();
    public Invokable getOnClickEffect();
    
}
