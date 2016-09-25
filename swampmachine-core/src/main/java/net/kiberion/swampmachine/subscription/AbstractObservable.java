package net.kiberion.swampmachine.subscription;

import java.util.Observable;

/**
 * Thread-safe observable
 * @author kibertoad
 *
 * @param <T>
 */
public abstract class AbstractObservable<T> extends Observable {

    protected abstract void setValueInternal(T newValue);

    public abstract T getValue ();
    
    public synchronized void setValue(T newValue) {
        setValueInternal(newValue);
        setChanged();
        notifyObservers(getValue());
    }

}
