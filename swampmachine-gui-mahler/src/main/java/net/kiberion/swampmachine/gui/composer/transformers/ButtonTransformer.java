package net.kiberion.swampmachine.gui.composer.transformers;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.gui.utils.InjectionUtils;
import net.kiberion.swampmachine.utils.SetUtils;
import net.kiberion.swampmachine.utils.common.ReflectionUtils;

public class ButtonTransformer extends AbstractTransformer<Map<String, Object>, SwampTextButton<MetadataHolderBlock>>{

    public static final Set<String> TRANSFORMED_PARAMETER = SetUtils.buildSet("buttons");

    @Autowired
    private TransformerHelper transformer;
    
    @Override
    public SwampTextButton<MetadataHolderBlock> transformSingle(Map<String,Object> parameter, Map<String, Object> context) {
        SwampTextButton<MetadataHolderBlock> button = new SwampTextButton<>((String) parameter.get("text"));
        List<Method> transformedInjectionMethods = ReflectionUtils.getSupportedMethodsWithAnnotation(SwampTextButton.class, InjectTransformedProperty.class, parameter.keySet());
        
        InjectionUtils.injectTransformedValues(button, transformedInjectionMethods, parameter, transformer, context);
        return button;
    };
    
    @Override
    public Collection<String> getParameterNames() {
        return TRANSFORMED_PARAMETER;
    }
}
