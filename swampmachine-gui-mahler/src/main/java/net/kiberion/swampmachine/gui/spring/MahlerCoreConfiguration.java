package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.gui.composer.CompositionRegistry;
import net.kiberion.swampmachine.gui.composer.transformers.InvokableEventTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.ListenerTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.SoundTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.TransformerHelper;
import net.kiberion.swampmachine.gui.composer.transformers.TransformerRegistry;
import net.kiberion.swampmachine.gui.composition.elements.CompositionElementDeserializer;
import net.kiberion.swampmachine.gui.templates.ElementTemplateRegistry;
import net.kiberion.swampmachine.registries.EventClassRegistry;
import net.kiberion.swampmachine.registries.ListenerClassRegistry;
import net.kiberion.swampmachine.utils.SetUtils;

@Configuration
public class MahlerCoreConfiguration {
    
    @Bean
    public CompositionRegistry compositionRegistry () {
        return new CompositionRegistry();
    }

    @Bean
    public ElementTemplateRegistry elementTemplateRegistry() {
        ElementTemplateRegistry registry = new ElementTemplateRegistry();;
        CompositionElementDeserializer.setTemplateRegistry(registry);
        return registry; 
    }
    
    @Bean
    public CompositionInjector compositionInjector() {
        return new CompositionInjector();
    }
    
    @Bean
    public TransformerRegistry transformerRegistry() {
        return new TransformerRegistry();
    }

    @Bean
    public EventClassRegistry eventClassRegistry () {
        return new EventClassRegistry(SetUtils.buildSet("net.kiberion"));
    }

    @Bean
    public ListenerClassRegistry listenerClassRegistry () {
        return new ListenerClassRegistry(SetUtils.buildSet("net.kiberion"));
    }
    
    @Bean
    public InvokableEventTransformer invokableEventTransformer() {
        return new InvokableEventTransformer();
    }

    @Bean
    public ListenerTransformer listenerTransformer() {
        return new ListenerTransformer();
    }
    
    @Bean
    public SoundTransformer soundTransformer() {
        return new SoundTransformer();
    }
    
    @Bean
    public TransformerHelper transformerHelper () {
        return new TransformerHelper();
    }
    
   
}
