package net.kiberion.swampmachine.subscription;

public class ObservableObject<T> extends AbstractObservable<T, T>{

    private T value = null;
    
    @Override
    public T getValue() {
        return value;
    }

    @Override
    protected void setValueInternal(T newValue) {
        value = newValue;
    }
}
