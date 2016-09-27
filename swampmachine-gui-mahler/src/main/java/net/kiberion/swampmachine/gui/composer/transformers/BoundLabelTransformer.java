package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.gui.elements.SwampLabel;
import net.kiberion.swampmachine.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.SwampScript;
import net.kiberion.swampmachine.scripting.SwampScriptInvokationResult;
import net.kiberion.swampmachine.subscription.AbstractObservable;

public class BoundLabelTransformer extends AbstractTransformer<String, SwampLabel>{

    public static final String TRANSFORMED_PARAMETER = "labelValue";

    @Autowired
    private ScriptEntityFactory scriptFactory;
    
    @Override
    public SwampLabel transformSingle(String parameter, Map<String, Object> context) {
        SwampBinding binding = (SwampBinding) context.get("binding");
        SwampScript valueScript = scriptFactory.getParserInstance().parseScript(parameter);
        
        SwampScriptInvokationResult result = valueScript.invoke(binding); 
        AbstractObservable<?> observable = result.getResult();
        SwampLabel button = new SwampLabel(observable);
        
        return button;
    };
    
    @Override
    public String getParameterName() {
        return TRANSFORMED_PARAMETER;
    }
}
