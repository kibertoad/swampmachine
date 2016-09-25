package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.api.ParameterTransformer;

public class TransformerHelper {

    @Autowired
    private TransformerRegistry transformerRegistry;

    @SuppressWarnings({ "rawtypes" })
    public Object getTransformedProperty(Map<String, Object> propertySource, String property, Map<String, Object> context) {
        Object value = propertySource.get(property);
        ParameterTransformer transformer = transformerRegistry.getTransformers().get(property);
        if (transformer == null) {
            return value;
        }
        return transformer.transform(value, context);
    }
}
