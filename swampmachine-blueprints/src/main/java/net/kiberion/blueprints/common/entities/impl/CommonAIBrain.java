package net.kiberion.blueprints.common.entities.impl;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.blueprints.common.entities.api.AIBrain;

public class CommonAIBrain<T extends Enum<?>> implements AIBrain<T>{

    @Getter
    @Setter
    private T type;
    
    public CommonAIBrain (T type) {
        this.setType(type);
    }
    
}
