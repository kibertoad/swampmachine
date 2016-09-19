package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import lombok.Getter;
import net.kiberion.swampmachine.gui.api.ParameterTransformer;

public class TransformerRegistry implements InitializingBean{

    @Autowired
    private ApplicationContext context;
    
    @Getter
    private final Map<String, ParameterTransformer> transformers = new HashMap<>();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        for (ParameterTransformer transformer : context.getBeansOfType(ParameterTransformer.class).values()) {
            Validate.isTrue(!transformers.containsKey(transformer.getParameterName()));
            transformers.put(transformer.getParameterName(), transformer);
        }
        
    }
    
}
