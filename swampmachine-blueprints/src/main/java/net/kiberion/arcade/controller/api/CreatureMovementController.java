package net.kiberion.arcade.controller.api;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.tiled.aspects.api.CollidableAspect;

public interface CreatureMovementController {

    /**
     * 
     * @param creature
     * @param movementDelta
     * @return true if managed to move
     */
    boolean moveCreature(CollidableAspect creature, PositionAspect movementDelta);

    void removeEntityFromView(MetadataHolderAspect entity);

}
