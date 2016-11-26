package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.invokables.ScriptInvokable;

public class ScriptTransformer extends AbstractTransformer<String, ScriptInvokable> {

    public static final String PARAMETER_PARAMETER = "onClickScript";

    @Autowired
    private ScriptEntityFactory scriptEntityFactory;
    
    @Override
    public ScriptInvokable transformSingle(String parameter, Map<String, Object> context) {
        Validate.notBlank(parameter);
        SwampBinding binding = (SwampBinding) context.get("binding");
        return new ScriptInvokable (scriptEntityFactory.getParserInstance().parseScript(parameter), binding);
    }

    @Override
    public String getParameterName() {
        return PARAMETER_PARAMETER;
    }

}
