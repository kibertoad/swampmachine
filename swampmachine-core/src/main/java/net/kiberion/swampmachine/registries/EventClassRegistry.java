package net.kiberion.swampmachine.registries;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ConstructableEntity;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;

/**
 * @author kibertoad
 *
 */
@ImmutableRegistry
public class EventClassRegistry {

    @Getter
    private Map<String, Class<? extends ApplicationEvent>> events = new HashMap<>();

    public EventClassRegistry(Collection<String> packagesToScan) {
        for (String packageName : packagesToScan) {
            Reflections reflections = new Reflections(packageName);
            Set<Class<? extends ApplicationEvent>> classes = reflections.getSubTypesOf(ApplicationEvent.class);

            for (Class<? extends ApplicationEvent> event : classes) {
                ConstructableEntity metadata = event.getAnnotation(ConstructableEntity.class);
                if (metadata != null) {
                    events.put(metadata.id(), event);
                }
            }
        }
    }

}
