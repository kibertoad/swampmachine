package net.kiberion.swampmachine.invokables;

import net.kiberion.swampmachine.subscription.AbstractObservable;

/**
 * Sets value of a target observable to the value of observed entity
 * @author kibertoad
 *
 */
public class ObservableSynchronizingObservingInvokable<T> implements ObservingInvokable<T>{

    private final AbstractObservable<T, T> target;
    
    public ObservableSynchronizingObservingInvokable(AbstractObservable<T, T> target) {
        this.target = target;
    }
    
    @Override
    public void invoke(T value) {
        target.setValue(value);
    }
    
}
