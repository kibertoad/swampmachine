package net.kiberion.swampmachine.registries;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import net.kiberion.swampmachine.annotations.ConstructableEntity;

public abstract class AbstractClassRegistry<T> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void scanPackages(Collection<String> packagesToScan, Class clazz) {
        for (String packageName : packagesToScan) {
            Reflections reflections = new Reflections(packageName);
            Set<Class> classes = reflections.getSubTypesOf(clazz);

            for (Class event : classes) {
                ConstructableEntity metadata = ((Class<?>) event).getAnnotation(ConstructableEntity.class);
                if (metadata != null) {
                    getEntities().put(metadata.id(), event);
                }
            }
        }
    }

    protected abstract Map<String, Class<? extends T>> getEntities();

}
