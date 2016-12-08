package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.api.ParameterTransformer;

public class TransformerHelper {

    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TransformerRegistry transformerRegistry;

    public Object getTransformedProperty(ParameterTransformer<?, ?> transformer, Map<String, Object> propertySource,
            String propertyId, Map<String, Object> context) {
        
        if (transformer == null) {
            transformer = transformerRegistry.getTransformers().get(propertyId);
        }

        Object value = propertySource.get(propertyId);
        if (transformer == null) {
            return value;
        }
        
        log.info("Transforming value: "+value+" for property "+propertyId);
        return transformer.transform(value, context);
    }

    public Object getTransformedProperty(Map<String, Object> propertySource, String property,
            Map<String, Object> context) {
        return getTransformedProperty(null, propertySource, property, context);
    }

    public ParameterTransformer<?, ?> getTransformerForClass (Class<? extends ParameterTransformer<?, ?>> transformerClass) {
        if (transformerClass == null) {
            return null;
        }
        return transformerRegistry.getTransformersByClass().get(transformerClass);
    }
    
}
