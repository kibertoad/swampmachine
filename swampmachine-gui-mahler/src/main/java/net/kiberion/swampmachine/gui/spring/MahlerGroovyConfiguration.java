package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.gui.composer.transformers.ScriptTransformer;
import net.kiberion.swampmachine.scripting.groovy.GroovyEntityFactory;

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
