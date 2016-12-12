package net.kiberion.swampmachine.gui.elements;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.Invokable;

public class CommonButtonEntry implements ButtonEntry{

    @Getter
    @Setter
    private String text;
    
    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private Invokable onClickEffect;
    
}
