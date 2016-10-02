package net.kiberion.persistence.test.mybatis;

import lombok.Getter;
import lombok.Setter;

public class Language {

    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String paradigm;
    
}
