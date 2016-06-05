package net.kiberion.swampmachine.factories;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import net.kiberion.entities.common.api.Invokable;
import net.kiberion.swampmachine.events.ChangeStateEvent;
import net.kiberion.swampmachine.invokables.PublishEventInvokable;

public class InvokablesFactory implements ApplicationEventPublisherAware{

    private ApplicationEventPublisher publisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public Invokable createStateChangeInvokable (String stateId) {
        return new PublishEventInvokable (publisher, new ChangeStateEvent (this, stateId)); 
    }
    
}
