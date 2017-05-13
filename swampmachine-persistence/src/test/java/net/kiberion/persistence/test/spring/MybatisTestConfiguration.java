package net.kiberion.persistence.test.spring;

import java.util.List;

import net.kiberion.swampmachine.spring.TestCoreConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.persistence.spring.AbstractInMemoryDBMybatisConfiguration;
import net.kiberion.persistence.test.mybatis.entities.LanguageMapper;
import net.kiberion.persistence.test.mybatis.entities.TestMybatisDao;
import net.kiberion.swampmachine.utils.common.InlineGList;

@Configuration
@Import({TestCoreConfiguration.class})
public class MybatisTestConfiguration extends AbstractInMemoryDBMybatisConfiguration {

    @Bean
    public TestMybatisDao dao () {
        return new TestMybatisDao();
    }
    
    @Override
    protected List<Class<?>> getMapperClasses() {
        return new InlineGList<>(LanguageMapper.class);
    }
    
}
