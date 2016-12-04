package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.api.scripting.SwampScript;
import net.kiberion.swampmachine.api.scripting.SwampScriptInvokationResult;
import net.kiberion.swampmachine.gui.elements.SwampLabel;
import net.kiberion.swampmachine.subscription.AbstractObservable;
import net.kiberion.swampmachine.utils.SetUtils;

public class BoundLabelTransformer extends AbstractTransformer<String, SwampLabel>{

    public static final Set<String> TRANSFORMED_PARAMETER = SetUtils.buildSet("labelValue");

    @Autowired
    private ScriptEntityFactory scriptFactory;
    
    @Override
    public SwampLabel transformSingle(String parameter, Map<String, Object> context) {
        SwampBinding binding = (SwampBinding) context.get("binding");
        SwampScript valueScript = scriptFactory.getParserInstance().parseScript(parameter);
        
        SwampScriptInvokationResult result = valueScript.invoke(binding); 
        AbstractObservable<?, ?> observable = result.getResult();
        SwampLabel button = new SwampLabel(observable);
        
        return button;
    };
    
    @Override
    public Collection<String> getParameterNames() {
        return TRANSFORMED_PARAMETER;
    }
}
