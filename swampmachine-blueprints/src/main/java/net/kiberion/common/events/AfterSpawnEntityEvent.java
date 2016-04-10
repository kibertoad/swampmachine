package net.kiberion.common.events;

import org.springframework.context.ApplicationEvent;

public class AfterSpawnEntityEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = -21018655845099754L;
    private final Object entity;
    
    public AfterSpawnEntityEvent(Object source, Object entity) {
        super(source);
        this.entity = entity;
    }
    
    public Object getEntity() {
        return entity;
    }

}
