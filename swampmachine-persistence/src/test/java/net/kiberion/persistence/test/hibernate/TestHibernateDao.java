package net.kiberion.persistence.test.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kiberion.persistence.AbstractDao;

@Transactional
@Repository("testDao")
public class TestHibernateDao extends AbstractDao<TestEntity> {

    @Override
    protected Class<TestEntity> getEntityClass() {
        return TestEntity.class;
    }
    
}
