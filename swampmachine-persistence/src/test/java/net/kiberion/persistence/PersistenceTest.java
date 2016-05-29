package net.kiberion.persistence;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.persistence.test.ContextBasedTest;

public class PersistenceTest extends ContextBasedTest{

	@Autowired
	private TestDao dao;
	
	//Wip
	@Ignore
	@Test
	public void testPersistEntity () {
		TestEntity entity = new TestEntity();
		dao.save(entity);
		
		assertNotNull (dao.findById(entity.getOid()));
	}
}
