package net.kiberion.swampmachine.gui.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.gui.composer.transformers.TransformerHelper;

public class InjectionUtils {

    private InjectionUtils() {
    }

    public static void injectTransformedValues(Object injectionTarget, Collection<Method> transformedInjectionMethods,
            Map<String, Object> propertySource, TransformerHelper transformer) {
        for (Method injectionMethod : transformedInjectionMethods) {
            NodeId metadata = injectionMethod.getAnnotation(NodeId.class);
            Object value = transformer.getTransformedProperty(propertySource, metadata.id());
            List<Object> args = new ArrayList<>();
            args.add(value);
            try {
                injectionMethod.invoke(injectionTarget, args.toArray());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
        }

    }
}
