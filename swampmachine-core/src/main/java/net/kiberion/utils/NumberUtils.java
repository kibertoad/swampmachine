package net.kiberion.utils;

public class NumberUtils {

    public static boolean isBetween (int value, int isOrAbove, int isOrBelow) {
        return (value >= isOrAbove) && (value <= isOrBelow);
    }
    
}
