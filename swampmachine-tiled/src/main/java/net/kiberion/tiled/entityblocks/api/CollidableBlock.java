package net.kiberion.tiled.entityblocks.api;

import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.entityblocks.api.PositionHolderBlock;
import net.kiberion.tiled.entityblocks.impl.FormHolderBlock;

public interface CollidableBlock extends FormHolderBlock, PositionHolderBlock, MetadataHolderBlock {

    public void processCollision(CollidableBlock collisionObject);

    public default boolean removeOnCollision() {
        return false;
    }

    public default boolean overlaps(FormHolderBlock formHolder) {
        return getFormAspect().getRectangle().overlaps(formHolder.getFormAspect().getRectangle());
    }
    
    public boolean canCollide (CollidableBlock collisionObject);

}
