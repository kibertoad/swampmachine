package net.kiberion.swampmachine.blueprints.common.loaders;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.blueprints.common.entities.impl.CreatureModelInfo;

public class CreatureRegistry {

    @Getter
    private final Map<String, CreatureModelInfo> creatures = new HashMap<>();
    
    
}
