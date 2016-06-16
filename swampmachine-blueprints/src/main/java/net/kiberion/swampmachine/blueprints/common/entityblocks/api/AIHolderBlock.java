package net.kiberion.swampmachine.blueprints.common.entityblocks.api;

import net.kiberion.swampmachine.blueprints.common.entities.api.AIBrain;

public interface AIHolderBlock<T extends Enum<?>> {

    public AIBrain<T> getAiBrain ();
    public void setAiBrain (AIBrain<T> brain);
    
}
