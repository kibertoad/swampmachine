package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import net.kiberion.swampmachine.annotations.ConstructableEntity;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.factories.InvokablesFactory;
import net.kiberion.swampmachine.registries.EventClassRegistry;
import net.kiberion.swampmachine.utils.common.ReflectionUtils;

public class InvokableEventTransformer extends AbstractTransformer<Map<String, Object>, Invokable> {

    public static final String PARAMETER_PARAMETER = "onClickEvent";

    @Autowired
    private InvokablesFactory invokablesFactory;

    @Autowired
    private EventClassRegistry registry;

    @Override
    public Invokable transformSingle(Map<String, Object> parameter, Map<String, Object> context) {
        Class<? extends ApplicationEvent> eventClass = registry.getEvents().get(parameter.get("type"));
        ConstructableEntity metadata = eventClass.getAnnotation(ConstructableEntity.class);

        Map<String, Object> constructorParameters = new HashMap<>(parameter);
        constructorParameters.put("source", new Object());
        ApplicationEvent event = ReflectionUtils.buildObject(metadata.constructorProperties(), eventClass, constructorParameters);
        return invokablesFactory.createEventInvokable(event);
    }

    @Override
    public String getParameterName() {
        return PARAMETER_PARAMETER;
    }

}
