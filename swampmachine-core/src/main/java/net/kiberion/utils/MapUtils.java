package net.kiberion.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kibertoad
 */
public class MapUtils {

    private MapUtils (){

    }

    public static <TKey, TValue> TValue getOrDefault (Map<TKey, TValue> map, TKey key, TValue defaultValue) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return defaultValue;
        }
    }

    public static <TKey> String getOrDefault(Map<TKey, String> map, TKey key, String defaultValue) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return defaultValue;
        }
    }


    public static List<Integer> getIntegerListSafe(Map map, String key) {
        return getIntegerListSafe(map, key, false);
    }

    public static List<Integer> getIntegerListSafe(Map map, String key, boolean decrementValues) {
        int delta;
        if (decrementValues) {
            delta = -1;
        } else {
            delta = 0;
        }
        Object o = map.get(key);
        List<Integer> result = new ArrayList<>();

        if (o instanceof Integer) {
            result.add(((Integer) o) + delta);
        } else if (o instanceof List) {
            for (Integer i : (List<Integer>) o) {
                result.add(i + delta);
            }
            return result;
        } else {

            if (!map.containsKey(key)) {
                throw new IllegalArgumentException("Unknown key: " + key);
            }

            String[] arr = ((String) map.get(key)).split(" ");
            for (String s : arr) {
                result.add(Integer.parseInt(s) + delta);
            }
        }

        return result;
    }


    public static Map<String, Object> buildMap (Object ... object) {
        Map<String, Object> map = new HashMap<>();

        for (int x = 0; x < object.length; x += 2) {
            map.put((String)object[x], object[x+1]);
        }

        return map;
    }
}
