package net.kiberion.swampmachine.invokables;

import org.apache.commons.lang3.Validate;

import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.api.scripting.SwampScript;

public class ScriptInvokable implements Invokable{

    private final SwampScript script;
    private final SwampBinding binding;
    
    public ScriptInvokable(SwampScript script, SwampBinding binding) {
        Validate.notNull(script);
        Validate.notNull(binding);
        this.script = script;
        this.binding = binding;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T invoke() {
        return (T) script.invoke(binding).getResult();                
    }
    
}
