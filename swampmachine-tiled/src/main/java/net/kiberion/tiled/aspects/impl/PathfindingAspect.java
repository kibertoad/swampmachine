package net.kiberion.tiled.aspects.impl;

import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.pathfinding.Pathfinder2;


public class PathfindingAspect {

    private static final Logger log = LogManager.getLogger();
    
    public int getNextDeltaX() {
        return nextDeltaX;
    }

    public int getNextDeltaY() {
        return nextDeltaY;
    }

    public int getNextPlannedX() {
        return (int) (positionAspect.getX() + nextDeltaX);
    }

    public int getNextPlannedY() {
        return (int) (positionAspect.getY() + nextDeltaY);
    }

    private final PositionAspect positionAspect;
    private PositionAspect targetPosition;
    private Pathfinder2 pathfinder;

    private int nextDeltaX;
    private int nextDeltaY;

    private int nextX;
    public int getNextX() {
        return nextX;
    }

    public int getNextY() {
        return nextY;
    }

    private int nextY;
    
    
    public PathfindingAspect(PositionAspect positionAspect) {
        super();
        this.positionAspect = positionAspect;
    }

    public void setMap(TiledMapInfo mapInfo, int maxSearchDistance, boolean setAllowDiagonalMovement, boolean strictDiagonalMovement) {
        this.pathfinder = new Pathfinder2(mapInfo.getPathfindingInfo(), maxSearchDistance, setAllowDiagonalMovement,
                positionAspect, strictDiagonalMovement);
    }

    public void setNewTargetPosition(PositionAspect targetPosition) {
        this.targetPosition = targetPosition;
        this.pathfinder.setTarget(targetPosition);
    }

    public boolean reachedNextStep() {
        
        if (!this.pathfinder.pathAvailable()) {
            return true;
        }
        
        //log.info("Position: "+this.positionAspect.toString());
        //log.info("nextX: "+this.getNextX());
        //log.info("nextY: "+this.getNextY());
        
        return (Precision.equals(this.positionAspect.getX(), this.getNextX(), 0.01f) && Precision.equals(this.positionAspect.getY(), this.getNextY(), 0.01f));
    }
    
    public void processTakenStep (){
        if (reachedNextStep ()){
            //log.info("Reached next step");
            this.positionAspect.setX(this.getNextX());
            this.positionAspect.setY(this.getNextY());
            recalculatePath();
        }
    }
    
    public void recalculateDelta (){
        if (this.pathfinder.pathAvailable()) {

            nextDeltaX = this.pathfinder.path.getX(1);
            nextDeltaY = this.pathfinder.path.getY(1);

            if (nextDeltaX > this.positionAspect.getIntX()) {
                nextDeltaX = 1;
            } else if (nextDeltaX < this.positionAspect.getIntX()) {
                nextDeltaX = -1;
            } else {
                nextDeltaX = 0;
            }

            if (nextDeltaY > this.positionAspect.getIntY()) {
                nextDeltaY = 1;
            } else if (nextDeltaY < this.positionAspect.getIntY()) {
                nextDeltaY = -1;
            } else {
                nextDeltaY = 0;
            }
        } else {
            nextDeltaX = 0;
            nextDeltaY = 0;
        }     
        
        nextX = this.positionAspect.getIntX() + nextDeltaX;
        nextY = this.positionAspect.getIntY() + nextDeltaY;
    }

    public void recalculatePath() {
        this.pathfinder.updatePath();

        recalculateDelta();

    }

    public String outputPath() {
        return this.pathfinder.path.toString();
    }

}
