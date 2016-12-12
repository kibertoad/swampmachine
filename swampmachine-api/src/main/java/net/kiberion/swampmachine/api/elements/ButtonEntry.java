package net.kiberion.swampmachine.api.elements;

import net.kiberion.swampmachine.api.invokables.Invokable;

public interface ButtonEntry extends TextEntry{

    public boolean isActive ();
    public Invokable getOnClickEffect();
    
}
