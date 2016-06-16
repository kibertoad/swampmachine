package net.kiberion.swampmachine.blueprints.arcade.controller.api;

import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.tiled.entityblocks.api.CollidableBlock;

public interface CreatureMovementController {

    /**
     * 
     * @param creature
     * @param movementDelta
     * @return true if managed to move
     */
    boolean moveCreature(CollidableBlock creature, CommonPosition movementDelta);

    void removeEntityFromView(MetadataHolderBlock entity);

}
