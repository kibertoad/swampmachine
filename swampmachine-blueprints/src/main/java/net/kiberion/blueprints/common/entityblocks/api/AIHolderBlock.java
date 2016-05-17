package net.kiberion.blueprints.common.entityblocks.api;

import net.kiberion.blueprints.common.entities.api.AIBrain;

public interface AIHolderBlock<T extends Enum<?>> {

    public AIBrain<T> getAiBrain ();
    public void setAiBrain (AIBrain<T> brain);
    
}
