package net.kiberion.tiled.aspects.api;

import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.entityblocks.api.PositionHolderBlock;
import net.kiberion.tiled.aspects.impl.FormHolderAspect;

public interface CollidableAspect extends FormHolderAspect, PositionHolderBlock, MetadataHolderBlock {

    public void processCollision(CollidableAspect collisionObject);

    public default boolean removeOnCollision() {
        return false;
    }

    public default boolean overlaps(FormHolderAspect formHolder) {
        return getFormAspect().getRectangle().overlaps(formHolder.getFormAspect().getRectangle());
    }
    
    public boolean canCollide (CollidableAspect collisionObject);

}
