package net.kiberion.persistence.test.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.kiberion.persistence.spring.AbstractInMemoryDBHibernateConfiguration;
import net.kiberion.persistence.test.hibernate.entities.TestEntity;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("net.kiberion.persistence.test.hibernate")
@Import({net.kiberion.swampmachine.spring.TestCoreConfiguration.class}) 
public class HibernateTestConfiguration extends AbstractInMemoryDBHibernateConfiguration {

    @Override
    protected Class<?>[] getAnnotatedClasses() {
        return new Class[] {TestEntity.class};
    }

}
