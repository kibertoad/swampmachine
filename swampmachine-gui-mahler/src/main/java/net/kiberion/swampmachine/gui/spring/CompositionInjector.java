package net.kiberion.swampmachine.gui.spring;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.composer.CompositionRegistry;
import net.kiberion.swampmachine.gui.composer.populators.Populator;

public class CompositionInjector implements ApplicationContextAware {

    private ApplicationContext context;

    @Autowired
    private CompositionRegistry registry;

    @Autowired
    private Populator populator;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void inject() {
        SpringBoundCompositionsExtractor compositionsExtractor = new SpringBoundCompositionsExtractor();
        SpringCompositionConsumerExtractor consumerExtractor = new SpringCompositionConsumerExtractor();

        Collection<CompositionConsumer> consumers = consumerExtractor.extractCompositionConsumersFromContext(context);

        for (CompositionConsumer consumer : consumers) {
            List<Composition> compositions = compositionsExtractor
                    .extractCompositionsForConsumer(registry.getCompositions(), consumer);
            populator.populate(consumer, compositions);
        }
    }

}
