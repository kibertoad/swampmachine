package net.kiberion.swampmachine.invokables;

import java.util.Observable;
import java.util.Observer;

import net.kiberion.entities.common.api.ParametrizedInvokable;

@FunctionalInterface
public interface ObservingInvokable<T> extends Observer, ParametrizedInvokable<T>{

    @SuppressWarnings("unchecked")
    @Override
    default void update(Observable source, Object newValue) {
        invoke((T) newValue);
    }
    
}
