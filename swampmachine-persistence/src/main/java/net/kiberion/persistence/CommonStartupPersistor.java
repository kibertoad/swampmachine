package net.kiberion.persistence;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;

/**
 * Common implementation of {@link Persistor} that persists entities from specified map using specified dao on startup.
 * 
 * @author kibertoad
 *
 * @param <T>
 */
@LoadOnStartup
public class CommonStartupPersistor<T extends Serializable> implements Persistor{

    private final Map<String, T> sourceMap;
    
    private final AbstractDao<T> targetDao;
    
    @Setter
    @Getter
    private int priority;
    
    public CommonStartupPersistor(Map<String, T> sourceMap, AbstractDao<T> targetDao) {
        Validate.notNull(sourceMap, "Source map is null.");
        Validate.notNull(targetDao, "Target dao is null.");
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
