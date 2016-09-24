package net.kiberion.swampmachine.gui.composer.transformers;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.gui.utils.InjectionUtils;
import net.kiberion.utils.ReflectionUtils;

public class ButtonTransformer extends AbstractTransformer<Map<String, Object>, SwampTextButton<MetadataHolderBlock>>{

    public static final String TRANSFORMED_PARAMETER = "buttons";

    @Autowired
    private TransformerHelper transformer;
    
    @Override
    public SwampTextButton<MetadataHolderBlock> transformSingle(Map<String,Object> parameter) {
        SwampTextButton<MetadataHolderBlock> button = new SwampTextButton<>((String) parameter.get("text"));
        List<Method> transformedInjectionMethods = ReflectionUtils.getSupportedMethodsWithAnnotation(SwampTextButton.class, InjectTransformedProperty.class, parameter.keySet());
        
        InjectionUtils.injectTransformedValues(button, transformedInjectionMethods, parameter, transformer);
        return button;
    };
    
    @Override
    public String getParameterName() {
        return TRANSFORMED_PARAMETER;
    }
}
