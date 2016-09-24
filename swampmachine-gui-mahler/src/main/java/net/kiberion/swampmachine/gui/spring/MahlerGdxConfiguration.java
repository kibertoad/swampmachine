package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.gui.composer.populators.GdxElementFactory;
import net.kiberion.swampmachine.gui.composer.populators.GdxPopulator;
import net.kiberion.swampmachine.gui.composer.transformers.ButtonTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.ImageTransformer;

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
    
    @Bean
    public ImageTransformer imageTransformer() {
        return new ImageTransformer();
    }
    
    @Bean
    public ButtonTransformer buttonTransformer() {
        return new ButtonTransformer();
    }

}
