package net.kiberion.swampmachine.assets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.AbstractModelEntityDescriptor;

public abstract class AbstractRegistry <T extends AbstractModelEntityDescriptor> {

    @Getter
    private List<T> entities = new ArrayList<>();

    private Map<String, T> entityMap = new HashMap<>();

    public void addAll(Collection<T> opportunities) {
        for (T entity : opportunities) {
            add(entity);
        }
    }

    public void add(T entity) {
        entities.add(entity);
        entityMap.put(entity.getId(), entity);
    }

}
