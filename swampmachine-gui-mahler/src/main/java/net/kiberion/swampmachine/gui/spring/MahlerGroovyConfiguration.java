package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.groovy.GroovyEntityFactory;
import net.kiberion.swampmachine.gui.composer.transformers.ScriptTransformer;

@Configuration
public class MahlerGroovyConfiguration {

    @Bean
    public ScriptEntityFactory scriptEntityFactory () {
        return new GroovyEntityFactory ();
    }

    @Bean
    public ScriptTransformer scriptTransformer () {
        return new ScriptTransformer();
    }
}
