package net.kiberion.swampmachine.gui.composer;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class CompositionRegistry {

    @Getter
    private final Map<String, Composition> compositions = new HashMap<>();
    
}
