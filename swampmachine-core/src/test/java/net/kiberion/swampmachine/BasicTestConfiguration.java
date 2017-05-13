package net.kiberion.swampmachine;

import net.kiberion.swampmachine.factories.entities.DummyAfterSpawnListener;
import net.kiberion.swampmachine.factories.entities.DummyFactory;
import net.kiberion.swampmachine.spring.SwampmachineExtrasConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SwampmachineExtrasConfiguration.class})
public class BasicTestConfiguration {

    @Bean
    public DummyFactory dummyFactory() {
        return new DummyFactory();
    }

    @Bean
    public DummyAfterSpawnListener dummyAfterSpawnListener() {
        return new DummyAfterSpawnListener();
    }
}
