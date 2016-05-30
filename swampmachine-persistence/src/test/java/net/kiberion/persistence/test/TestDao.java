package net.kiberion.persistence.test;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kiberion.persistence.AbstractDao;
import net.kiberion.persistence.TestEntity;

@Transactional
@Repository("testDao")
public class TestDao extends AbstractDao<TestEntity> {

    @Override
    protected Class<TestEntity> getEntityClass() {
        return TestEntity.class;
    }
    
}
