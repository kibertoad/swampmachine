package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.gui.composer.CompositionRegistry;
import net.kiberion.swampmachine.gui.composer.transformers.TransformerRegistry;

@Configuration
public class MahlerCoreConfiguration {
    
    @Bean
    public CompositionRegistry compositionRegistry () {
        return new CompositionRegistry();
    }
    
    @Bean
    public CompositionInjector compositionInjector() {
        return new CompositionInjector();
    }
    
    @Bean
    public TransformerRegistry transformerRegistry() {
        return new TransformerRegistry();
    }
    
}
