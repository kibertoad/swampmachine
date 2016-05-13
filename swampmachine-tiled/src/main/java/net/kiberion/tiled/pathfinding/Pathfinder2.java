package net.kiberion.tiled.pathfinding;

import net.kiberion.slick2d.util.pathfinding.AStarPathFinder;
import net.kiberion.slick2d.util.pathfinding.Path;
import net.kiberion.slick2d.util.pathfinding.TileBasedMap;
import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;

/**
 * @author kibertoad
 */
public class Pathfinder2 {

    private TileBasedMap map;
    private int searchDistance;
    private boolean allowDiagMovement;
    private boolean strictDiagMovement;

    private PositionAspect hostPosition;
    private PositionAspect targetPosition;

    //public int goalX;
    //public int goalY;

    public Path path;

    public Pathfinder2(TileBasedMap setMap, int maxSearchDistance, boolean setAllowDiagMovement, PositionAspect setHostPosition, boolean strictDiagonalMovement) {
        map = setMap;
        searchDistance = maxSearchDistance;
        allowDiagMovement = setAllowDiagMovement;
        this.strictDiagMovement = strictDiagonalMovement;

        hostPosition = setHostPosition;
    }

    /*
    public void setTarget(int toX, int toY) {
        goalX = toX;
        goalY = toY;
    }
    */
    
    public void setTarget(PositionAspect position) {
        //setTarget ((int)position.getX(), (int)position.getY());
        targetPosition = position;
    }

    public boolean pathAvailable() {
        //return (path != null && path.getLength() > 1);
        return (path != null);
    }


    public void updatePath() {
        AStarPathFinder pathFinder = new AStarPathFinder(map, searchDistance, allowDiagMovement, strictDiagMovement);

        path = pathFinder.findPath(null, (int) hostPosition.getX(), (int) hostPosition.getY(), (int)targetPosition.getX(), (int)targetPosition.getY());
    }

    public void resetPath() {
        path = null;
    }
}
