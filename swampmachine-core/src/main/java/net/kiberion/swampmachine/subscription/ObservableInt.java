package net.kiberion.swampmachine.subscription;

import org.springframework.util.ObjectUtils;

public class ObservableInt extends AbstractObservable<Integer> {

    private Integer value;

    public ObservableInt(int i) {
        value = Integer.valueOf(i);
    }
    
    public ObservableInt() {
        this (0);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    protected void setValueInternal(Integer newValue) {
        value = newValue;
    }

    public void increment() {
        setValue(value + 1);
    }

    public void decrement() {
        setValue(value - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObservableInt) {
            return ObjectUtils.nullSafeEquals(value, ((ObservableInt) obj).getValue());
        } else if (obj instanceof Integer) {
            return ObjectUtils.nullSafeEquals(value, obj);
        }

        return false;
    }

    @Override
    public int hashCode() {
        if (value != null) {
            return value.hashCode();
        }
        return super.hashCode();
    }
}
