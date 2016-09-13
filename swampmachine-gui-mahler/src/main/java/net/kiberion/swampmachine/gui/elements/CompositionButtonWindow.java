package net.kiberion.swampmachine.gui.elements;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class CompositionButtonWindow extends CompositionWindow <CompositionButton>{

    @Getter
    private final List<WeakReference<CompositionButton>> children = new ArrayList<>();
    
}
