package net.kiberion.swampmachine.mvcips.states.api;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import lombok.Getter;

public abstract class AbstractStateController implements ApplicationEventPublisherAware{

    @Getter
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    public void show() {
    }
    
}
