package net.kiberion.swampmachine.utils;

public class NumberUtils {

    private NumberUtils () {
    }
    
    public static boolean isBetween (int value, int isOrAbove, int isOrBelow) {
        return (value >= isOrAbove) && (value <= isOrBelow);
    }
    
}
