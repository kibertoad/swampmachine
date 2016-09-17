package net.kiberion.swampmachine.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.ReflectionUtils;
import org.springframework.context.ApplicationContext;

import com.google.common.collect.ImmutableMap;

import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.utils.predicates.AssignableFromPredicate;

/**
 * Used to convert registries to immutable maps after the initialization phase
 * 
 * @author kibertoad
 *
 */
public class ImmutableRegistryPreparer {

    private static final Logger log = LogManager.getLogger();

    private ImmutableRegistryPreparer() {
    }

    /**
     * Extracts all beans with annotation {@link ImmutableRegistry} from Spring
     * context and converts all fields with the type {@link Map} to
     * {@link ImmutableMap}.
     * 
     * @param context
     */
    @SuppressWarnings("unchecked")
    public static void invoke(ApplicationContext context) {
        Collection<Object> registries = context.getBeansWithAnnotation(ImmutableRegistry.class).values();

        for (Object registry : registries) {
            Set<Field> mapFields = ReflectionUtils.getAllFields(registry.getClass(),
                    new AssignableFromPredicate(Map.class));
            for (Field mapField : mapFields) {
                try {
                    mapField.setAccessible(true);
                    Map<?, ?> sourceMap = (Map<?, ?>) mapField.get(registry);
                    if (sourceMap != null && !(sourceMap instanceof ImmutableMap)) {
                        mapField.set(registry, ImmutableMap.copyOf(sourceMap));
                        log.info("Convert to immutable map: " + registry + " -> " + mapField);
                    }
                    mapField.setAccessible(false);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

}
