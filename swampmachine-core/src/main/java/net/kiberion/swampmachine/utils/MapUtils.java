package net.kiberion.swampmachine.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;

import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;

/**
 * @author kibertoad
 */
public class MapUtils {

    private MapUtils (){

    }

    public static List<Integer> getIntegerListSafe(Map<?, ?> map, String key) {
        return getIntegerListSafe(map, key, false);
    }

    //ToDo JavaDoc
    @SuppressWarnings("unchecked")
    public static List<Integer> getIntegerListSafe(Map<?, ?> map, String key, boolean decrementValues) {
        int delta;
        if (decrementValues) {
            delta = -1;
        } else {
            delta = 0;
        }
        Object o = map.get(key);
        List<Integer> result = new ArrayList<>();

        if (o instanceof Integer) {
            result.add(((Integer) o) + delta);
        } else if (o instanceof List) {
            for (Integer i : (List<Integer>) o) {
                result.add(i + delta);
            }
            return result;
        } else {

            if (!map.containsKey(key)) {
                throw new IllegalArgumentException("Unknown key: " + key);
            }

            String[] arr = ((String) map.get(key)).split(" ");
            for (String s : arr) {
                result.add(Integer.parseInt(s) + delta);
            }
        }

        return result;
    }


    public static <T> Map<String, T> buildMap (Object ... object) {
        if (object == null) {
            return Collections.emptyMap();
        }
        
        Validate.isTrue(((object.length & 1) == 0), "Length should be even. Received: "+Arrays.toString(object));
        Map<String, T> map = new HashMap<>();

        for (int x = 0; x < object.length; x += 2) {
            map.put((String)object[x], (T) object[x+1]);
        }

        return map;
    }
    
    public static <T extends IdHolderBlock> Map<String, T> toMap (Collection<T> sourceCollection) {
        Map<String, T> result = new HashMap<>();
        for (T entity : sourceCollection) {
            result.put(entity.getId(), entity);
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
	public <T> T getRandom (Map<?, ?> entityMap) {
        int indexValue = Dice.getRandomInt(0, entityMap.size());
        return (T) entityMap.values().toArray()[indexValue];
    }
    
    public static <T extends EntityModelDescriptor> void putAllEntities(Map<String, T> targetMap, Collection<T> sourceCollection) {
    	for (T entity : sourceCollection) {
    		targetMap.put(entity.getId(), entity);
    	}
    }

    public static <T extends IdHolderBlock> void putAllEntities(Map<String, T> targetMap, Map <String, T> sourceMap) {
        for (Entry<String, T> entityEntry : sourceMap.entrySet()) {
            targetMap.put(entityEntry.getKey(), entityEntry.getValue());
        }
    }
    
    public static <T extends IdHolderBlock> void removeAll(Map<String, T> mapToModify, Collection<T> objectsToRemove) {
        for (IdHolderBlock idHolder : objectsToRemove) {
            mapToModify.remove(idHolder.getId());
        }
    }    
    
}
