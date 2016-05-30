package net.kiberion.persistence;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.persistence.test.ContextBasedTest;
import net.kiberion.persistence.test.TestDao;

public class PersistenceTest extends ContextBasedTest{

	@Autowired
	private TestDao dao;
	
	@Test
	public void testPersistEntity () {
		TestEntity entity = new TestEntity();
		dao.save(entity);
		
		assertNotNull (dao.findById(entity.getOid()));
		dao.delete(entity);
		assertNull (dao.findById(entity.getOid()));
	}
}
