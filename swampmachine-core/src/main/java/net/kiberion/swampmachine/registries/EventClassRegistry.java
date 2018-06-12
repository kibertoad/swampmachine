package net.kiberion.swampmachine.registries;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;

/**
 * @author kibertoad
 *
 */
@ImmutableRegistry
public class EventClassRegistry extends AbstractClassRegistry<ApplicationEvent>{

    @Getter
    private Map<String, Class<? extends ApplicationEvent>> events = new HashMap<>();

    public EventClassRegistry(Collection<String> packagesToScan) {
        scanPackages (packagesToScan, ApplicationEvent.class);
    }

    @Override
    protected Map<String, Class<? extends ApplicationEvent>> getEntities() {
        return events;
    }

}
