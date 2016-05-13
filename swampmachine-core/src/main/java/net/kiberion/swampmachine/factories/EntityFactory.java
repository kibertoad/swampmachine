package net.kiberion.swampmachine.factories;

import java.util.List;

import net.kiberion.swampmachine.factories.params.SpawnParams;


public interface EntityFactory <T, P extends SpawnParams> {

    public List<Class<?>> getSupportedClasses();
    
    public T spawnEntity (P params);
}
