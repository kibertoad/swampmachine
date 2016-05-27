package net.kiberion.persistence;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;
import lombok.Setter;

@PersistOnStartup
public class CommonStartupPersistor<T extends Serializable> implements Persistor{

    private final Map<String, T> sourceMap;
    
    private final AbstractDao<T> targetDao;
    
    @Setter
    @Getter
    private int priority;
    
    
    public CommonStartupPersistor(Map<String, T> sourceMap, AbstractDao<T> targetDao) {
        super();
        this.sourceMap = sourceMap;
        this.targetDao = targetDao;
    }



    @Override
    public void persistEntities() {
        for (Entry<String, T> entry : sourceMap.entrySet()) {
            targetDao.save(entry.getValue());
        }
        
    }

}
