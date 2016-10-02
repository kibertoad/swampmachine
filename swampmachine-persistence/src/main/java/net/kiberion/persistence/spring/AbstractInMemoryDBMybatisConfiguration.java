package net.kiberion.persistence.spring;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public abstract class AbstractInMemoryDBMybatisConfiguration {
    
    @Bean
    public HikariConfig hikariConfig () {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.hsqldb.jdbcDriver");
        config.setJdbcUrl("jdbc:hsqldb:mem:memdb");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        return config;
    }
    
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }
    
    @Bean
    public TransactionFactory transactionFactory() {
        return new JdbcTransactionFactory();
    }
    
    @Bean
    public Environment environment () {
        return new Environment("development", transactionFactory(), dataSource());
    }

    protected abstract List<Class<?>> getMapperClasses();
    
    @Bean
    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration(environment());
        for (Class<?> clazz : getMapperClasses()) {
            conf.addMapper(clazz);
        }
        return conf;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        return new SqlSessionFactoryBuilder().build(configuration());
    }
    
    @Bean
    public SqlSession sqlSession() {
        return new SqlSessionTemplate (sqlSessionFactory()); 
    }
    
    protected String getLiquibaseChangelogPath () {
        return "classpath:liquibase/structure/01-structure-changesets.xml";
    }
    
    @Bean
    public SpringLiquibase springLiquibase () {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource());
        springLiquibase.setChangeLog(getLiquibaseChangelogPath());
        return springLiquibase;
    }

}
