package net.kiberion.swampmachine.api.invokables;

/**
 * @author kibertoad
 * Multipurpose interface for passing invokable pieces of code as parameters
 */
@FunctionalInterface
public interface ParametrizedInvokable<T> {
    public void invoke(T param);
}
