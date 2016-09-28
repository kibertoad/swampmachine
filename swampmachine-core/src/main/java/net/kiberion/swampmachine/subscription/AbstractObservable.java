package net.kiberion.swampmachine.subscription;

import java.util.Observable;

/**
 * Thread-safe observable
 * @author kibertoad
 *
 * @param <T>
 */
public abstract class AbstractObservable<I, O> extends Observable {

    protected abstract void setValueInternal(I newValue);

    public abstract O getValue ();
    
    public synchronized void setValue(I newValue) {
        setValueInternal(newValue);
        setChanged();
        notifyObservers(getValue());
    }

}
