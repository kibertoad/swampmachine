package net.kiberion.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kibertoad
 */
public class SetUtils {

    private SetUtils() {
    }

    //if values have index meaning, they should be incremented for display purposes
    public static String integerSetToString(Set<Integer> integerSet, boolean incrementValues) {
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (Integer s : integerSet) {
            counter++;
            if (!incrementValues) {
                builder.append(s.toString());
            } else {
                builder.append(Integer.toString(s + 1));
            }
            if (counter < integerSet.size()) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static String integerSetToString(Set<Integer> integerSet) {
        return integerSetToString(integerSet, false);
    }

    public static Set<Integer> buildIntSet(int... i) {
        Set<Integer> result = new HashSet<>();
        for (int value : i) {
            result.add(value);
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> buildSet(T... i) {
        Set<T> result = new HashSet<>();
        for (T value : i) {
            result.add(value);
        }
        return result;
    }
    
}
