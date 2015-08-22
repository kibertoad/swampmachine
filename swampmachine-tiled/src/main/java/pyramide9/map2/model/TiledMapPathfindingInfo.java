package pyramide9.map2.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pyramide9.slick2d.util.pathfinding.PathFindingContext;
import pyramide9.slick2d.util.pathfinding.TileBasedMap;

public class TiledMapPathfindingInfo implements TileBasedMap{

    private static final Logger log = LogManager.getLogger();
    
    private final TiledMapInfo mapInfo;

    public TiledMapPathfindingInfo(TiledMapInfo mapInfo) {
        super();
        this.mapInfo = mapInfo;
    }

    @Override
    public int getTileWidth() {
        return mapInfo.getTileWidth();
    }

    @Override
    public int getTileHeight() {
        return mapInfo.getTileHeight();
    }

    @Override
    public int getWidthInTiles() {
        return mapInfo.getMapWidth();
    }

    @Override
    public int getHeightInTiles() {
        return mapInfo.getMapHeight();
    }

    @Override
    public void pathFinderVisited(int x, int y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean blocked(PathFindingContext context, int tx, int ty) {
        //log.info("Check for being blocked: "+tx+"/"+ty+": "+mapInfo.getWallInfo().isWall(tx, ty));
        return mapInfo.getWallInfo().isWall(tx, ty);
    }

    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
        return 0;
    }
    
}
