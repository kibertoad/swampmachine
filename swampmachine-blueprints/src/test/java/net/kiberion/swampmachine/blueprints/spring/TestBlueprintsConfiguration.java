package net.kiberion.swampmachine.blueprints.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.swampmachine.blueprints.common.spring.CommonBlueprintsLoaderConfiguration;
import net.kiberion.swampmachine.spring.TestCoreConfiguration;

@Configuration
@Import({ CommonBlueprintsLoaderConfiguration.class })
public class TestBlueprintsConfiguration extends TestCoreConfiguration {

}
