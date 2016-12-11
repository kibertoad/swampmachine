package net.kiberion.swampmachine.utils;

import java.util.ArrayList;
import java.util.Collection;

import net.kiberion.swampmachine.subscription.ObservableObject;

public class ObservableUtils {

    private ObservableUtils(){}
    
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> toEntityCollection (Collection<ObservableObject<T>> sourceCollection) {
        Collection<T> result = new ArrayList<>();
        for (ObservableObject<?> observable : sourceCollection) {
            result.add((T) observable.getValue());
        }
        return result;
    }
    
}
