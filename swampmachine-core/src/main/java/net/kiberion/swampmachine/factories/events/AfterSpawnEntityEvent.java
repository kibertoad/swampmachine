package net.kiberion.swampmachine.factories.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.kiberion.swampmachine.factories.MetaFactory;

/**
 * Event that gets published after {@link MetaFactory} has finished spawning a new entity.
 * 
 * @author kibertoad
 *
 */
public class AfterSpawnEntityEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = -21018655845099754L;
    
    @Getter
    private final Object entity;
    
    public AfterSpawnEntityEvent(Object source, Object entity) {
        super(source);
        this.entity = entity;
    }
    
}
