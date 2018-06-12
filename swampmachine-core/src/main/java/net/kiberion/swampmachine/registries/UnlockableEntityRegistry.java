package net.kiberion.swampmachine.registries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.entityblocks.api.EntityProvider;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;

/**
 * 
 * Note that this implementation is not thread-safe, hence it is advised to
 * accumulate all changes to this registry in a synchronized set and apply at
 * the end of distributed calculations.
 * 
 * Recommended way to use this class - create a class that would extend this class with appropriate generic and
 * mark it with {@link org.springframework.stereotype.Component} and {@link ImmutableRegistry} annotations.
 * 
 * @author kibertoad
 *
 * @param <T>
 */
public class UnlockableEntityRegistry<T extends MetadataHolderBlock> implements EntityProvider<T> {

    @Getter
    private Map<String, T> entityMap = new HashMap<>();
    
    private Set<T> unlockedEntities = new HashSet<>();

    @Override
    public Collection<T> getAllEntities() {
        return new ArrayList<> (entityMap.values());
    }
    
    public Collection<T> getAllUnlockedEntities() {
        return CollectionUtils.retainAll(entityMap.values(), unlockedEntities);
    }

    @Override
    public T getEntity(String id) {
        return entityMap.get(id);
    }

    public void unlock(String id) {
        unlockedEntities.add(getEntity(id));
    }

}
