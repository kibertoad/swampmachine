package net.kiberion.tiled.processors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.tiled.entityblocks.api.FormBlock;
import net.kiberion.tiled.model.TiledMapInfo;

public class WallObstacleProcessor {

    private static final Logger log = LogManager.getLogger();

    
    private TiledMapInfo mapInfo;

    
    public TiledMapInfo getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(TiledMapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }
    
    private boolean isTileBlocked (int x, int y) {
        return mapInfo.getWallInfo().isWall(x, y);
    }

    public boolean isTileBlocked (Position position) {
        //log.info("actual coords:"+position.toString());
        //log.info("Tested coords: "+(int) position.getX()+"/"+ (int) position.getY());
        
        Position invertedPosition = position.invertY(mapInfo.getMapHeight());
        boolean result =  isTileBlocked((int) invertedPosition.getX(), (int) invertedPosition.getY());
        
        if (result) {
            //getMapInfo().getWallInfo().outputAllWalls();
            log.info("Wall position: "+invertedPosition.toString());
        }
        return result;
    }

   
    public boolean objectOverlapsObstacle (Position position, float objectWidth, float objectHeight) {
        //log.info("Entity model position after move: "+position.toString());
        
        if (isTileBlocked (position)) {
            log.debug("Exact match");
            return true;
        }
        
        
        if (isTileBlocked (position.produceCloneWithAppliedDelta(objectWidth, objectHeight))) {
            log.debug("Match 1");
            return true;
        }
        
        if (isTileBlocked (position.produceCloneWithAppliedDelta(objectWidth, 0))) {
            log.debug("Match 2");
            return true;
        }
        
        if (isTileBlocked (position.produceCloneWithAppliedDelta(0, objectHeight))) {
            log.debug("Match 3");
            return true;
        }
        
        
        return false;
    }
    
    
    public boolean objectOverlapsObstacle (Position position) {
        return objectOverlapsObstacle(position, 0.98f, 0.98f);
     }
    
    public boolean objectOverlapsObstacle (Position position, FormBlock form) {
        return objectOverlapsObstacle(position, form.getWidthInTiles(), form.getHeightInTiles());
    }    
    
    
}
