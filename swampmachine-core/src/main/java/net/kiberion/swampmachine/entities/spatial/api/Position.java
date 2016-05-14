package net.kiberion.swampmachine.entities.spatial.api;



/**
 *
 * @author kibertoad
 */
public interface Position {
    
    public float getX();
    public float getY();
    
    public int getIntX();
    public int getIntY();
    
    public Position setX(float nextX);
    public Position setY(float nextY);
    
    public Position produceCloneWithAppliedDelta(Position delta);
    public Position produceCloneWithAppliedDelta(float x, float y);
    
    public void applyDelta(float deltaX, float deltaY);
    public float distanceTo (Position other);    
    
    public Position invertY(int mapHeight);
    
}
