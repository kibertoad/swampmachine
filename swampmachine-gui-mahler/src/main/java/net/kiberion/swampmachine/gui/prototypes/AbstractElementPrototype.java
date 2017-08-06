package net.kiberion.swampmachine.gui.prototypes;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractElementPrototype implements ElementPrototype{

    @Getter
    @Setter
    private float x;

    @Getter
    @Setter
    private float y;

}
