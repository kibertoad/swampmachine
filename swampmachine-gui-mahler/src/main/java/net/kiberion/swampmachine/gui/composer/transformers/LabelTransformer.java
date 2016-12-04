package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.kiberion.swampmachine.gui.elements.SwampLabel;
import net.kiberion.swampmachine.utils.SetUtils;

public class LabelTransformer extends AbstractTransformer<String, SwampLabel>{

    public static final Set<String> TRANSFORMED_PARAMETER = SetUtils.buildSet("labelText");
    
    @Override
    public SwampLabel transformSingle(String parameter, Map<String, Object> context) {
        return new SwampLabel(parameter);
    };
    
    @Override
    public Collection<String> getParameterNames() {
        return TRANSFORMED_PARAMETER;
    }
}
