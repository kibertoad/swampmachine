package net.kiberion.swampmachine.entities.common.api;

/**
 * @author kibertoad
 * Multipurpose interface for passing invokable pieces of code as parameters
 */
@FunctionalInterface
public interface InvokableWithEntity<T> {
    public void invoke(T entity);
}
