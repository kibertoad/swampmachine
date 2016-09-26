package net.kiberion.swampmachine.gui.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty.ElementTransformedProperties;
import net.kiberion.swampmachine.gui.api.ParameterTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.TransformerHelper;

public class InjectionUtils {

    private static final Logger log = LogManager.getLogger();

    private InjectionUtils() {
    }

    private static Class<? extends ParameterTransformer<?, ?>> getTransformerForProperty(String property,
            Object injectionTarget) {
        ElementTransformedProperties transformationsSource = injectionTarget.getClass()
                .getAnnotation(ElementTransformedProperties.class);
        ElementTransformedProperty transformation = injectionTarget.getClass()
                .getAnnotation(ElementTransformedProperty.class);

        ElementTransformedProperty[] transformations = null;
        if (transformationsSource != null) {
            transformations = transformationsSource.value();
        } else

        if (transformation != null) {
            transformations = new ElementTransformedProperty[] { transformation };
        }

        if (transformations != null) {
            for (ElementTransformedProperty transformedProperty : transformations) {
                if (StringUtils.equals(property, transformedProperty.sourceProperty())) {
                    return transformedProperty.targetTransformer();
                }
            }
        }
        return null;
    }

    public static void injectTransformedValues(Object injectionTarget, Collection<Method> transformedInjectionMethods,
            Map<String, Object> propertySource, TransformerHelper transformerHelper, Map<String, Object> context) {
        for (Method injectionMethod : transformedInjectionMethods) {
            NodeId metadata = injectionMethod.getAnnotation(NodeId.class);

            ParameterTransformer<?, ?> transformer = transformerHelper
                    .getTransformerForClass(getTransformerForProperty(metadata.id(), injectionTarget));
            Object value = transformerHelper.getTransformedProperty(transformer, propertySource, metadata.id(),
                    context);
            List<Object> args = new ArrayList<>();
            args.add(value);
            try {
                injectionMethod.invoke(injectionTarget, args.toArray());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                log.error("Error while invoking '" + injectionMethod + "' with args: " + args + " into "
                        + injectionTarget);
                log.error("Transformed property: " + metadata.id());
                throw new IllegalStateException(e);
            }
        }

    }
}
