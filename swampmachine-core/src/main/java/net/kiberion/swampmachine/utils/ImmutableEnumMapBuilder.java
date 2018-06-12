package net.kiberion.swampmachine.utils;

import java.util.EnumMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class ImmutableEnumMapBuilder<K extends Enum<K>, V> {

    private EnumMap<K, V> map;
    
    public ImmutableEnumMapBuilder(Map<K, V> sourceMap) {
        map = new EnumMap<> (sourceMap);
    }
    
    public ImmutableEnumMapBuilder(Class<K> enumClass) {
        map = new EnumMap<> (enumClass);
    }
    
    
    public ImmutableEnumMapBuilder<K, V> put (K key, V value) {
        map.put(key, value);
        return this;
    }
    
    public Map<K, V> build () {
        return ImmutableMap.copyOf(map);
    }
    
}
