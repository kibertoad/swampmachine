package net.kiberion.blueprints.common.processors.random.api;

public interface RandomProvider {

    /**
     * Returns random int that is between given min and max (inclusive)
     * @param minInclusive
     * @param maxInclusive
     * @return
     */
    public int getRandomInt (int minInclusive, int maxInclusive);
    
}
