package net.kiberion.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersistenceTest {

	@Autowired
	private TestDao dao;
	
	@Test
	public void testPersistEntity () {
		TestEntity entity = new TestEntity();
		dao.save(entity);
		
		assertNotNull (dao.findById(entity.getOid()));
	}
}
