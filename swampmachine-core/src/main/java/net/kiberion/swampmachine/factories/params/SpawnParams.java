package net.kiberion.swampmachine.factories.params;

/**
 * Interface for parameters that will be used to spawn a new entity. At the very
 * least each implementation should specify what entity should be spawned (by
 * providing ID)
 * 
 * @author kibertoad
 *
 */
public interface SpawnParams {

    public String getId();

}
