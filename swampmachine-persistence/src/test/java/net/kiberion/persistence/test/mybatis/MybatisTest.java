package net.kiberion.persistence.test.mybatis;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.persistence.test.mybatis.entities.Language;
import net.kiberion.persistence.test.mybatis.entities.TestMybatisDao;
import net.kiberion.persistence.test.spring.ContextBasedMybatisTest;

public class MybatisTest extends ContextBasedMybatisTest {

    @Autowired
    private TestMybatisDao dao;

    @Test
    public void testMybatis() {
        assertEquals(0, dao.getAllLanguages().size());

        Language java = new Language();
        java.setParadigm("oop");
        java.setName("java");

        dao.insertLanguage(java);
        List<Language> results = dao.getAllLanguages();
        assertEquals(1, results.size());
        Language receivedLanguage = results.get(0);
        assertEquals("java", receivedLanguage.getName());
    }

}
