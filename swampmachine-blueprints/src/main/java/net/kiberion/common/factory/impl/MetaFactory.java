package net.kiberion.common.factory.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import net.kiberion.common.events.AfterSpawnEntityEvent;
import net.kiberion.common.events.SpawnEntityEvent;
import net.kiberion.factories.EntityFactory;
import net.kiberion.factories.SpawnParams;

@Component
public class MetaFactory implements ApplicationEventPublisherAware,
        ApplicationListener<SpawnEntityEvent>, ApplicationContextAware, InitializingBean {

    @SuppressWarnings("rawtypes")
    private Map<Class<?>, EntityFactory> factoryMap;
    private ApplicationEventPublisher eventPublisher;
    private ApplicationContext ctx;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(SpawnEntityEvent event) {
        EntityFactory<?, SpawnParams> factory = factoryMap.get(event.getClazz());
        if (factory == null) {
            throw new IllegalArgumentException ("No factory for "+event.getClazz());
        }

        Object entity = factory.spawnEntity(event.getSpawnParams());
        eventPublisher.publishEvent(new AfterSpawnEntityEvent(factory, entity));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        factoryMap = new HashMap<>();

        for (EntityFactory<?, SpawnParams> factory : ctx.getBeansOfType(EntityFactory.class).values()) {
            for (Class<?> clazz : factory.getSupportedClasses()) {
                if (factoryMap.containsKey(clazz)) {
                    throw new IllegalStateException(
                            "Ambiguous factory definition for class "
                                    + clazz.getCanonicalName());
                }
                factoryMap.put(clazz, factory);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

}
