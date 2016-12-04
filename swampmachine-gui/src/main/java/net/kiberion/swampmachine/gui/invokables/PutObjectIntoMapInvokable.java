package net.kiberion.swampmachine.gui.invokables;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.api.invokables.Invokable;

public class PutObjectIntoMapInvokable<K, V> implements Invokable{

    private static final Logger log = LogManager.getLogger();
    
    private final Map<K, V> targetMap;
    private final K targetKey;
    private final V targetValue;
    
    public PutObjectIntoMapInvokable(Map<K, V> targetMap, K targetKey, V targetValue) {
        super();
        this.targetMap = targetMap;
        this.targetKey = targetKey;
        this.targetValue = targetValue;
    }

    @Override
    public <T> T invoke() {
        targetMap.put(targetKey, targetValue);
        log.info("Put to map: "+targetValue);
        return null;
    }

}
