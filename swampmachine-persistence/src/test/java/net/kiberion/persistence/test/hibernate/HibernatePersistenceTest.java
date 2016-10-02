package net.kiberion.persistence.test.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.persistence.test.spring.ContextBasedHibernateTest;

public class HibernatePersistenceTest extends ContextBasedHibernateTest{

	@Autowired
	private TestHibernateDao dao;
	
	@Test
	public void testPersistEntity () {
		TestEntity entity = new TestEntity();
		dao.save(entity);
		
		assertNotNull (dao.findById(entity.getOid()));
		dao.delete(entity);
		assertNull (dao.findById(entity.getOid()));
	}
}
