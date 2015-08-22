package net.kiberion.entities.common.api;


/**
 * @author kibertoad
 */
public interface Toggleable extends Recalculable{

    public void enable ();
    public void disable ();

    public boolean isEnabled();

}
