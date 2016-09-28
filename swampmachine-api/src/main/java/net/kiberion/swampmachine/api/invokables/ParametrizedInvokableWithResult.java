package net.kiberion.swampmachine.api.invokables;

/**
 * @author kibertoad
 * Multipurpose interface for passing invokable pieces of code as parameters that also returns invokation result
 */
@FunctionalInterface
public interface ParametrizedInvokableWithResult<T, R> {
    public R invoke(T param);
}
