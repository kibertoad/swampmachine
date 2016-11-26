package net.kiberion.persistence.test.mybatis.entities;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface LanguageMapper {

    @Results({ 
        @Result(property = "id", column = "id"), 
        @Result(property = "name", column = "name"),
        @Result(property = "paradigm", column = "paradigm") 
        })
    @Select("SELECT id from languages WHERE id = #{id}")
    Language selectLanguage(int id);

    @Select("SELECT * from languages")
    List<Language> selectAllLanguages();

    @Insert("INSERT into languages(name,paradigm) VALUES(#{name}, #{paradigm})")
    void insertLanguage(Language language);
    
}
