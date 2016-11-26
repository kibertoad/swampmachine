package net.kiberion.swampmachine.utils.common;

public class NumberUtils {

    private NumberUtils () {
    }
    
    public static boolean isBetween (int value, int isOrAbove, int isOrBelow) {
        return (value >= isOrAbove) && (value <= isOrBelow);
    }
    
}
