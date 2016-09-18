package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.gui.composer.populators.GdxElementFactory;
import net.kiberion.swampmachine.gui.composer.populators.GdxPopulator;

@Configuration
public class MahlerGdxConfiguration {
    
    @Bean
    public GdxElementFactory elementFactory() {
        return new GdxElementFactory();
    }

    @Bean
    public GdxPopulator populator() {
        return new GdxPopulator();
    }

    
}
