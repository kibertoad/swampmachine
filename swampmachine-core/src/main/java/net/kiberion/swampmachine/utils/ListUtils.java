package net.kiberion.swampmachine.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    private ListUtils() {
    }

    @SafeVarargs
    public static <T> List<T> buildList(T... entries) {
        List<T> result = new ArrayList<>();
        if (entries == null) {
            return result;
        }
        for (T entry : entries) {
            result.add(entry);
        }
        return result;
    }

}
