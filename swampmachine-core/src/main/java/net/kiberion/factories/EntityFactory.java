package net.kiberion.factories;

import java.util.List;


public interface EntityFactory <T, P extends SpawnParams> {

    public List<Class<?>> getSupportedClasses();
    
    public T spawnEntity (P params);
}
