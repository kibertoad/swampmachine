package net.kiberion.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("testDao")
public class TestDao extends HibernateDaoSupport {

	public void save(TestEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(TestEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public void delete(TestEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public TestEntity findById(String id) {
		return getHibernateTemplate().get(TestEntity.class, id);
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

}
