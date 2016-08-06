package net.kiberion.persistence.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.kiberion.persistence.TestEntity;
import net.kiberion.persistence.spring.AbstractInMemoryDBConfiguration;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("net.kiberion.persistence.test")
@Import({net.kiberion.swampmachine.spring.TestCoreConfiguration.class}) 
public class TestConfiguration extends AbstractInMemoryDBConfiguration {

    @Override
    protected Class<?>[] getAnnotatedClasses() {
        return new Class[] {TestEntity.class};
    }

}
