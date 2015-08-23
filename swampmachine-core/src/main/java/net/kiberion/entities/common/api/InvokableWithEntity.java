package net.kiberion.entities.common.api;

/**
 * @author kibertoad
 */
public interface InvokableWithEntity<T> {
    public void invoke(T entity);
}
