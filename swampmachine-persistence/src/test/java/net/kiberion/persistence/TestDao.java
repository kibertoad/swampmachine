package net.kiberion.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("testDao")
public class TestDao extends AbstractDao<TestEntity> {

    @Override
    protected Class<TestEntity> getEntityClass() {
        return TestEntity.class;
    }
    
}
