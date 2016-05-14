package net.kiberion.arcade.controller.api;

import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.tiled.aspects.api.CollidableAspect;

public interface CreatureMovementController {

    /**
     * 
     * @param creature
     * @param movementDelta
     * @return true if managed to move
     */
    boolean moveCreature(CollidableAspect creature, CommonPosition movementDelta);

    void removeEntityFromView(MetadataHolderBlock entity);

}
