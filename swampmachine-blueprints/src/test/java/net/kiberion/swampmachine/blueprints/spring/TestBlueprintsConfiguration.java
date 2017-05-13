package net.kiberion.swampmachine.blueprints.spring;

import net.kiberion.swampmachine.spring.TestCoreConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.swampmachine.blueprints.common.spring.CommonBlueprintsLoaderConfiguration;

@Configuration
@Import({ CommonBlueprintsLoaderConfiguration.class })
public class TestBlueprintsConfiguration extends TestCoreConfiguration {

}
