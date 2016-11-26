package net.kiberion.persistence.test.mybatis.entities;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TestMybatisDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Language> getAllLanguages() {
        LanguageMapper mapper = sqlSession.getMapper(LanguageMapper.class);
        return mapper.selectAllLanguages();
    }

    @Transactional
    public void insertLanguage(Language language) {
        LanguageMapper mapper = sqlSession.getMapper(LanguageMapper.class);
        mapper.insertLanguage(language);
    }
    

}
