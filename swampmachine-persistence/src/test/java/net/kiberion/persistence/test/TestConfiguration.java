package net.kiberion.persistence.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.kiberion.swampmachine.spring.CoreConfiguration;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
//@EnableJpaRepositories("some.root.package")
public class TestConfiguration extends CoreConfiguration {

}
