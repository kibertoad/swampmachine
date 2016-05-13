package net.kiberion.swampmachine.entities.spatial.api;



/**
 *
 * @author kibertoad
 */
public interface PositionAspect {
    
    public float getX();
    public float getY();
    
    public int getIntX();
    public int getIntY();
    
    public PositionAspect setX(float nextX);
    public PositionAspect setY(float nextY);
    
    public PositionAspect produceCloneWithAppliedDelta(PositionAspect delta);
    public PositionAspect produceCloneWithAppliedDelta(float x, float y);
    
    public void applyDelta(float deltaX, float deltaY);
    public float distanceTo (PositionAspect other);    
    
    public PositionAspect invertY(int mapHeight);
    
}
