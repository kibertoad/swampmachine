package net.kiberion.blueprints.common.loaders;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.blueprints.common.entities.impl.CreatureModelInfo;

public class CreatureRegistry {

    @Getter
    private final Map<String, CreatureModelInfo> creatures = new HashMap<>();
    
    
}
