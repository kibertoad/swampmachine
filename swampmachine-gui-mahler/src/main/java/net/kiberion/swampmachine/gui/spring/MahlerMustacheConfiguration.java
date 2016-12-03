package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.api.templating.TemplateFactory;
import net.kiberion.swampmachine.gui.composition.elements.CompositionElementDeserializer;
import net.kiberion.swampmachine.templating.mustache.MustacheTemplateFactory;

@Configuration
public class MahlerMustacheConfiguration {

    @Bean
    public TemplateFactory templateFactory() {
        TemplateFactory templateFactory = new MustacheTemplateFactory();
        CompositionElementDeserializer.setTemplateFactory(templateFactory);
        return templateFactory;
    }

}
