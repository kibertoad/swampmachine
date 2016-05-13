package net.kiberion.common.processors.random.impl;

import java.util.Random;

import net.kiberion.common.processors.random.api.RandomProvider;

public class CommonRandomProvider implements RandomProvider{

    private final Random rng = new Random();
    
    @Override
    public int getRandomInt(int minInclusive, int maxInclusive) {
        return rng.nextInt(maxInclusive - minInclusive) + minInclusive;
    }

}
