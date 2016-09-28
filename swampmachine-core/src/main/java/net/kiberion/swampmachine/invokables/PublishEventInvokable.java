package net.kiberion.swampmachine.invokables;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import net.kiberion.swampmachine.api.invokables.Invokable;

public class PublishEventInvokable implements Invokable{

    private final ApplicationEventPublisher publisher;
    private final ApplicationEvent event;
    
    public PublishEventInvokable(ApplicationEventPublisher publisher, ApplicationEvent event) {
        super();
        this.publisher = publisher;
        this.event = event;
    }

    @Override
    public <T> T invoke() {
        publisher.publishEvent(event);
        return null;
    }
}
