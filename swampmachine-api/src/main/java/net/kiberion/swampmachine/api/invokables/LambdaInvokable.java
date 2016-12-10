package net.kiberion.swampmachine.api.invokables;

/**
 * This interface is more convenient for consumption within lambdas when you don't care for the value returned
 * @author kibertoad
 *
 */
public interface LambdaInvokable extends Invokable{

    @SuppressWarnings("unchecked")
    @Override
    public Object invoke();
    
}
