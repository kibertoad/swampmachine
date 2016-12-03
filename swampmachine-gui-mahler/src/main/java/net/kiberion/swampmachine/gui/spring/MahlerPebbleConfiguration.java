package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.api.templating.TemplateFactory;
import net.kiberion.swampmachine.gui.composition.elements.CompositionElementDeserializer;
import net.kiberion.swampmachine.templating.pebble.PebbleTemplateFactory;

@Configuration
public class MahlerPebbleConfiguration {

    @Bean
    public TemplateFactory templateFactory() {
        TemplateFactory templateFactory = new PebbleTemplateFactory();
        CompositionElementDeserializer.setTemplateFactory(templateFactory);
        return templateFactory;
    }

}
