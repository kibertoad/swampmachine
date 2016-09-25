package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.gui.listeners.onclick.OnClickScriptListener;
import net.kiberion.swampmachine.scripting.SwampBinding;

public class ScriptTransformer extends AbstractTransformer<String, OnClickScriptListener> {

    public static final String PARAMETER_PARAMETER = "onClickScript";

    @Autowired
    private ScriptEntityFactory scriptEntityFactory;
    
    @Override
    public OnClickScriptListener transformSingle(String parameter, Map<String, Object> context) {
        Validate.notBlank(parameter);
        SwampBinding binding = (SwampBinding) context.get("binding");
        return new OnClickScriptListener (scriptEntityFactory.getParserInstance().parseScript(parameter), binding);
    }

    @Override
    public String getParameterName() {
        return PARAMETER_PARAMETER;
    }

}
