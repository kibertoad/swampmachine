package net.kiberion.swampmachine.factories;

import org.springframework.context.ApplicationListener;

import net.kiberion.swampmachine.factories.events.AfterSpawnEntityEvent;

/**
 * Processes all entities of T class after their AfterSpawnEntityEvent was fired up
 * @author kibertoad
 *
 * @param <T>
 */
public abstract class AfterSpawnListener<T> implements ApplicationListener<AfterSpawnEntityEvent> {

    public abstract Class<T> getSupportedClass ();
    
    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(AfterSpawnEntityEvent event) {
        if (getSupportedClass().isInstance(event.getEntity())) {
            processEntityAfterSpawn((T) event.getEntity());
        }
    }
    
    protected abstract void processEntityAfterSpawn (T entity);
    
}
