package net.kiberion.tiled.processors;

import java.util.HashMap;
import java.util.Map;

import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;

public class SimpleCollisionInfoProvider implements CollisionInfoProvider {

    private Map<PositionAspect, Boolean> collidableObjectsMap = new HashMap<>();
    
    @Override
    public boolean isOccupiedByCollidable(int x, int y) {
        PositionAspect position = new CommonPosition (x, y);
        Boolean value = collidableObjectsMap.get(position);
        return value != null && value;
    }
    
    public void setCellCollidable (int x, int y, boolean collidable) {
        collidableObjectsMap.put(new CommonPosition(x, y),  Boolean.valueOf(collidable));
    }

}
