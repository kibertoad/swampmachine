package net.kiberion.tiled.aspects.api;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.aspects.api.PositionHolderAspect;
import net.kiberion.tiled.aspects.impl.FormHolderAspect;

public interface CollidableAspect extends FormHolderAspect, PositionHolderAspect, MetadataHolderAspect {

    public void processCollision(CollidableAspect collisionObject);

    public default boolean removeOnCollision() {
        return false;
    }

    public default boolean overlaps(FormHolderAspect formHolder) {
        return getFormAspect().getRectangle().overlaps(formHolder.getFormAspect().getRectangle());
    }
    
    public boolean canCollide (CollidableAspect collisionObject);

}
