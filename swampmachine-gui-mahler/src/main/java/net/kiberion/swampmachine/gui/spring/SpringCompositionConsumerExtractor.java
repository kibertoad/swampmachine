package net.kiberion.swampmachine.gui.spring;

import java.util.Collection;

import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.gui.api.CompositionConsumer;

public class SpringCompositionConsumerExtractor {


    public Collection<CompositionConsumer> extractCompositionConsumersFromContext(ApplicationContext context) {
        return context.getBeansOfType(CompositionConsumer.class).values();
    }
    
    
}
