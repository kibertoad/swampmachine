package net.kiberion.swampmachine.blueprints.common.entities.api;

public interface AIBrain<T extends Enum<?>> {

    public T getType ();
    public void setType (T type);
    
}
