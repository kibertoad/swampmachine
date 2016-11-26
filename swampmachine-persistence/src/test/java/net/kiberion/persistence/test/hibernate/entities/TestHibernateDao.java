package net.kiberion.persistence.test.hibernate.entities;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kiberion.persistence.hibernate.AbstractHibernateDao;

@Transactional
@Repository("testDao")
public class TestHibernateDao extends AbstractHibernateDao<TestEntity> {

    @Override
    public Class<TestEntity> getEntityClass() {
        return TestEntity.class;
    }
    
}
