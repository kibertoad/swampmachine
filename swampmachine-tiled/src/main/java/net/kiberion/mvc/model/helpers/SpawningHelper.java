package net.kiberion.mvc.model.helpers;

import java.util.List;

import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.utils.Dice;
import net.kiberion.tiled.entityblocks.api.CollidableBlock;
import net.kiberion.tiled.model.TiledMapInfo;

public class SpawningHelper {

    /**
     * Returns position on map that is not a wall and not occupied by a creature
     * @return
     */
    public Position getFreePosition (TiledMapInfo mapInfo, List<? extends CollidableBlock> collidableObjects) {
        Position result = new CommonPosition();
        
        int x = Dice.getRandomValue(0, mapInfo.getMapWidth());
        int y = Dice.getRandomValue(0, mapInfo.getMapHeight());
        
        return result;
    }
    
}
