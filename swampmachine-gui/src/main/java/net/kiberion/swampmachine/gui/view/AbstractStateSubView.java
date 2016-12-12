package net.kiberion.swampmachine.gui.view;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import lombok.Getter;

public class AbstractStateSubView<T> extends AbstractStateView<T> implements ApplicationEventPublisherAware{

    @Getter
    private ApplicationEventPublisher eventPublisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
    
    @Override
    protected void initStage() {
        //Do nothing, subViews get bound to their parent's stage.
    }
    
    @Override
    protected Collection<AbstractStateSubView<?>> getAutoEnabledSubViews() {
        return Collections.emptyList();
    }
    
}
