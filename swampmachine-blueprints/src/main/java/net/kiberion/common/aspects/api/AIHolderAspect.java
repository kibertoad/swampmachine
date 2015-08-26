package net.kiberion.common.aspects.api;

import net.kiberion.common.entities.api.AIBrain;

public interface AIHolderAspect<T extends Enum<?>> {

    public AIBrain<T> getAiBrain ();
    public void setAiBrain (AIBrain<T> brain);
    
}
