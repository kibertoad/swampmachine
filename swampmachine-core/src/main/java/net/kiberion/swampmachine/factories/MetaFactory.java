package net.kiberion.swampmachine.factories;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;

import net.kiberion.swampmachine.factories.events.AfterSpawnEntityEvent;
import net.kiberion.swampmachine.factories.events.SpawnEntityEvent;
import net.kiberion.swampmachine.factories.params.SpawnParams;

/**
 * 
 * Higher-level factory that resolves exact factory to use for producing
 * requested entity. Works by processing {@link SpawnEntityEvent} events that
 * arrive via {@link ApplicationEventPublisher} Spring mechanism.
 * <p>
 * After entity is spawned, {@link AfterSpawnEntityEvent} event gets fired.
 * 
 * @author kibertoad
 *
 */
public class MetaFactory implements ApplicationEventPublisherAware, ApplicationListener<SpawnEntityEvent>,
        ApplicationContextAware, InitializingBean {

    private Map<Class<?>, EntityFactory<?, SpawnParams>> factoryMap = new HashMap<>();
    private ApplicationEventPublisher eventPublisher;
    private ApplicationContext ctx;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;

    }

    @Override
    public void onApplicationEvent(SpawnEntityEvent event) {
        EntityFactory<?, SpawnParams> factory = factoryMap.get(event.getEntityClass());
        Validate.notNull(factory, "No factory for " + event.getEntityClass());

        Object entity = factory.spawnEntity(event.getSpawnParams());
        eventPublisher.publishEvent(new AfterSpawnEntityEvent(factory, entity));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        for (EntityFactory<?, SpawnParams> factory : ctx.getBeansOfType(EntityFactory.class).values()) {
            for (Class<?> clazz : factory.getSupportedClasses()) {
                if (factoryMap.containsKey(clazz)) {
                    throw new IllegalStateException(
                            "Ambiguous factory definition for class " + clazz.getCanonicalName());
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
