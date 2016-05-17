package net.kiberion.swampmachine.factories;

import java.util.List;

import net.kiberion.swampmachine.factories.params.SpawnParams;

/**
 * Interface used by factories for {@link MetaFactory}.
 * Implementations produce objects based on provided parameters
 * 
 * @author kibertoad
 *
 * @param <T> objects of what class are produced by this factory
 * @param <P> class for the parameter object that is used for producing entities
 */
public interface EntityFactory <T, P extends SpawnParams> {

    /**
     * @return classes, objects of which this factory can produce. Can never be empty or {@code null}
     */
    public List<Class<?>> getSupportedClasses();
    
    /**
     * 
     * @param params Parameters that should be used for spawning an entity
     * @return spawned new instance of an object. Can never return {@code null}
     */
    public T spawnEntity (P params);
    
}
