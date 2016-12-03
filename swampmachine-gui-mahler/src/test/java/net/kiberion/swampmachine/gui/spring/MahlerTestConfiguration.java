package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.gui.composer.CompositionLoader;
import net.kiberion.swampmachine.gui.composition.elements.ElementPrototypeRegistry;
import net.kiberion.swampmachine.gui.templates.ElementTemplateLoader;
import net.kiberion.swampmachine.spring.CommonLoaderConfiguration;
import net.kiberion.swampmachine.spring.TestCoreConfiguration;

@Configuration
@Import(value = { MahlerCoreConfiguration.class, MahlerGdxConfiguration.class, CommonGuiConfiguration.class, CommonLoaderConfiguration.class,
        MahlerGroovyConfiguration.class})
public class MahlerTestConfiguration extends TestCoreConfiguration {

    @Bean
    public TestState state() {
        return new TestState();
    }

    @Bean
    public ReaderHelper readerHelper() {
        return new ReaderHelper();
    }

    @Bean
    public CompositionLoader compositionLoader() {
        return new CompositionLoader(100);
    }

    @Bean
    public ElementTemplateLoader elementTemplateLoader() {
        return new ElementTemplateLoader(50);
    }
    
    @Bean
    public ElementPrototypeRegistry elementRegistry() {
        return new ElementPrototypeRegistry("net.kiberion.swampmachine.gui.elements");
    }
    
}
