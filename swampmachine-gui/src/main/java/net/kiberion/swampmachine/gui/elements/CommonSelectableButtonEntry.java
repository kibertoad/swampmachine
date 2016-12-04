package net.kiberion.swampmachine.gui.elements;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.api.elements.SelectableButtonEntry;
import net.kiberion.swampmachine.api.invokables.Invokable;

public class CommonSelectableButtonEntry extends CommonButtonEntry implements SelectableButtonEntry {

    @Getter
    private final List<Invokable> onSelectEffects = new ArrayList<>();
    
    @Getter
    private final List<Invokable> onDeselectEffects = new ArrayList<>();

    @Getter
    @Setter
    private boolean canSelect;
    
    @Getter
    @Setter
    private boolean canDeselect;

    @Getter
    @Setter
    private boolean selected;

}
