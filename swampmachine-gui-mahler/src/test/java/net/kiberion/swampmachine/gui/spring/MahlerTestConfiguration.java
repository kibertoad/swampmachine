package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.gui.composer.CompositionLoader;

@Configuration
@Import(value = { MahlerCoreConfiguration.class })
public class MahlerTestConfiguration {

    @Bean
    public TestState state () {
        return new TestState();
    }
    
    @Bean
    public ReaderHelper readerHelper() {
        return new ReaderHelper();
    }

    @Bean
    public CompositionLoader compositionLoader() {
        return new CompositionLoader();
    }
    
    @Bean
    public GameConfig gameConfig () {
        GameConfig config = new GameConfig();
        config.setPathToResourcesAsString("src/test/resources");
        return config;
    }
    
}
