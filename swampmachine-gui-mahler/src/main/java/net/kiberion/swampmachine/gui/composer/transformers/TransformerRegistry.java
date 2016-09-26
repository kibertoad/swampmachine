package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.Getter;
import net.kiberion.swampmachine.gui.api.ParameterTransformer;

public class TransformerRegistry implements InitializingBean, ApplicationContextAware{

    private ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
    
    @Getter
    private final Map<String, ParameterTransformer<?, ?>> transformers = new HashMap<>();
    
    @Getter
    private final Map<Class<? extends ParameterTransformer>, ParameterTransformer<?, ?>> transformersByClass = new HashMap<>();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        for (ParameterTransformer<?, ?> transformer : context.getBeansOfType(ParameterTransformer.class).values()) {
            Validate.isTrue(!transformers.containsKey(transformer.getParameterName()), "No transformer for property: "+transformer.getParameterName());
            transformers.put(transformer.getParameterName(), transformer);
            transformersByClass.put(transformer.getClass(), transformer);
        }
        
    }
    
}
