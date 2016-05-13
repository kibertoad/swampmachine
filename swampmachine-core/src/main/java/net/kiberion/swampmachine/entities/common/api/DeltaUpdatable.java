package net.kiberion.swampmachine.entities.common.api;

/**
 * 
 * @author kibertoad
 * 
 * Entity that is intended to be updated in realtime
 *
 */
public interface DeltaUpdatable {

    /**
     * Amount of time passed since last update
     * @param delta
     */
    public void update (float delta);
    
}
