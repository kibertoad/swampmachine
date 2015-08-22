package pyramide9.map2.aspects.interfaces;

import net.kiberion.aspects.api.PositionHolderAspect;
import pyramide9.map2.aspects.holders.FormHolderAspect;
import pyramide9.map2.aspects.holders.MetadataHolderAspect;

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
