package net.kiberion.swampmachine.blueprints.common.registries;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.blueprints.common.entities.api.Trait;

public class TraitRegistry<T extends Trait> {

    @Getter
    private final Map<String, T> traits = new HashMap<>();
    
}
