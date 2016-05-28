package net.kiberion.entities.common.api;

/**
 * @author kibertoad
 * Multipurpose interface for passing invokable pieces of code as parameters
 */
@FunctionalInterface
public interface ParametrizedInvokable<T> {
    public void invoke(T param);
}
