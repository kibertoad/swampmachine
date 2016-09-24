package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import net.kiberion.swampmachine.annotations.ConstructableEntity;
import net.kiberion.swampmachine.registries.ListenerClassRegistry;
import net.kiberion.utils.ReflectionUtils;

public class ListenerTransformer extends AbstractTransformer<Map<String, Object>, ChangeListener> {

    public static final String PARAMETER_PARAMETER = "onClickListener";

    @Autowired
    private ListenerClassRegistry registry;

    @Override
    public ChangeListener transformSingle(Map<String, Object> parameter) {
        Class<? extends ChangeListener> eventClass = registry.getListeners().get(parameter.get("type"));
        ConstructableEntity metadata = eventClass.getAnnotation(ConstructableEntity.class);

        Map<String, Object> constructorParameters = new HashMap<>(parameter);
        ChangeListener event = ReflectionUtils.buildObject(metadata.constructorProperties(), eventClass, constructorParameters);
        return event;
    }

    @Override
    public String getParameterName() {
        return PARAMETER_PARAMETER;
    }

}
