package net.kiberion.common.processors.random.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import net.kiberion.common.processors.random.api.RandomProvider;

/**
 * Thread-safe deterministic random provider
 * @author kibertoad
 *
 */
public class DeterministicRandomProvider implements RandomProvider{

    @Getter
    private final List<Integer> values = new ArrayList<>();
    
    private AtomicInteger counter = new AtomicInteger();
    
    
    public void addValues (Integer... integers) {
    	for (Integer value : integers) {
    		values.add(value);
    	}
    }
    
    public void setValues (Integer... integers) {
        counter.set(0);
        values.clear();
        addValues (integers);
    }
    
    
    @Override
    public int getRandomInt(int minInclusive, int maxInclusive) {
        int result =  values.get(counter.getAndIncrement());
        
        int currentValue = counter.intValue(); 
        if (currentValue >= values.size()) {
            counter.compareAndSet(currentValue, 0); //only reset if some other thread didn't do that already
        }
        
        if (result > maxInclusive) {
        	result = maxInclusive;
        }
        
        if (result < minInclusive) {
        	result = minInclusive;
        }
        
        return result;
    }
}
