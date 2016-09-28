package net.kiberion.swampmachine.factories;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.events.ChangeStateEvent;
import net.kiberion.swampmachine.invokables.PublishEventInvokable;

public class InvokablesFactory implements ApplicationEventPublisherAware{

    private ApplicationEventPublisher publisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public Invokable createStateChangeInvokable (String stateId) {
        return createEventInvokable (new ChangeStateEvent (this, stateId)); 
    }

    public Invokable createEventInvokable (ApplicationEvent event) {
        return new PublishEventInvokable (publisher, event); 
    }
    
}
