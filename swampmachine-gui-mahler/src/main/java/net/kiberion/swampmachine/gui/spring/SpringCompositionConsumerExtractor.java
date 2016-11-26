package net.kiberion.swampmachine.gui.spring;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.api.composition.CompositionConsumer;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;

public class SpringCompositionConsumerExtractor {

    public Collection<CompositionConsumer> extractCompositionConsumersFromContext(ApplicationContext context) {
        return context.getBeansOfType(CompositionConsumer.class).values().stream()
                .filter(entry -> entry.getClass().getAnnotation(BoundCompositions.class) != null)
                .collect(Collectors.toList());
    }

}
