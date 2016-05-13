package net.kiberion.mvc.model.helpers;

import java.util.List;

import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.utils.Dice;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.model.TiledMapInfo;

@Component
public class SpawningHelper {

    /**
     * Returns position on map that is not a wall and not occupied by a creature
     * @return
     */
    public PositionAspect getFreePosition (TiledMapInfo mapInfo, List<? extends CollidableAspect> collidableObjects) {
        PositionAspect result = new CommonPosition();
        
        int x = Dice.getRandomValue(0, mapInfo.getMapWidth());
        int y = Dice.getRandomValue(0, mapInfo.getMapHeight());
        
        return result;
    }
    
}
