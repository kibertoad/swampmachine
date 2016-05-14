package net.kiberion.swampmachine.factories.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.kiberion.swampmachine.factories.MetaFactory;
import net.kiberion.swampmachine.factories.params.SpawnParams;

/**
 * Event that signalizes an entity of a given glass should be created using
 * provided parameters. Expected default Swampmachine behaviour in this case is
 * that an event will be processed by {@link MetaFactory}, but alternative
 * implementations may be provided instead.
 * 
 * 
 * @author kibertoad
 *
 */
public class SpawnEntityEvent extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = -3000090314377929752L;
    private final SpawnParams spawnParams;

    @Getter
    private final Class<?> entityClass;

    public SpawnEntityEvent(Object source, Class<?> entityClass, SpawnParams spawnParams) {
        super(source);
        this.spawnParams = spawnParams;
        this.entityClass = entityClass;
    }

    public SpawnParams getSpawnParams() {
        return spawnParams;
    }

}
