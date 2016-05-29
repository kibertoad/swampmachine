package net.kiberion.swampmachine.jruby;

import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;

import net.kiberion.swampmachine.scripting.SwampScriptInvokationResult;

public class RubyResultWrapper implements SwampScriptInvokationResult {

    private final Map<String, Object> map;

    public RubyResultWrapper(Bindings map) {
        this.map = new HashMap<> (map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getVariableValue(String varName) {
        return (T) map.get(varName);
    }

}
