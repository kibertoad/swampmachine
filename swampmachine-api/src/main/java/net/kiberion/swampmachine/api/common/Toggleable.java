package net.kiberion.swampmachine.api.common;


/**
 * @author kibertoad
 */
public interface Toggleable {

    /**
     * Enables entity in question. Exact effect depends on entity.
     * Implementations of this interface guarantee that:
     * 1) Enabling already enabled entity does nothing.
     * 2) isEnabled methods returns true for enabled entities
     */
    public void enable ();
    
    /**
     * Disables entity in question. Exact effect depends on entity.
     * Implementations of this interface guarantee that:
     * 1) Disabling already enabled entity does nothing.
     * 2) isEnabled methods returns false for enabled entities
     */
    public void disable ();

    public boolean isEnabled();

}
