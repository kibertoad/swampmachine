package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Map;

import net.kiberion.swampmachine.gui.elements.SwampLabel;

public class LabelTransformer extends AbstractTransformer<String, SwampLabel>{

    public static final String TRANSFORMED_PARAMETER = "labelText";
    
    @Override
    public SwampLabel transformSingle(String parameter, Map<String, Object> context) {
        return new SwampLabel(parameter);
    };
    
    @Override
    public String getParameterName() {
        return TRANSFORMED_PARAMETER;
    }
}
