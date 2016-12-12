package net.kiberion.swampmachine.utils;

import java.util.List;

public class RandomUtils {

    private RandomUtils() {}
    
    public static <T> T getRandomEntry (List<T> sourceList) {
        int index = Dice.getRandomInt(1, sourceList.size());
        return sourceList.get(index - 1);
    }

    public static <T> T removeRandomEntry (List<T> sourceList) {
        int index = Dice.getRandomInt(1, sourceList.size());
        return sourceList.remove(index - 1);
    }
    
}
