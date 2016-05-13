package net.kiberion.common.events;

import org.springframework.context.ApplicationEvent;

import net.kiberion.swampmachine.factories.params.SpawnParams;

public class SpawnEntityEvent extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = -3000090314377929752L;
    private final SpawnParams spawnParams;
    private final Class<?> clazz;

    public SpawnEntityEvent(Object source, Class<?> clazz, SpawnParams spawnParams) {
        super(source);
        this.spawnParams = spawnParams;
        this.clazz = clazz;
    }

    public SpawnParams getSpawnParams() {
        return spawnParams;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
