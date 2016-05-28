package net.kiberion.swampmachine.jython;

import org.python.core.Py;
import org.python.core.PyBoolean;
import org.python.core.PyInteger;
import org.python.core.PyStringMap;

import net.kiberion.swampmachine.scripting.SwampScriptInvokationResult;

public class PyMapWrapper implements SwampScriptInvokationResult{

    private final PyStringMap map;

    public PyMapWrapper(PyStringMap map) {
        super();
        this.map = map;
    }
    
    public int getInteger (String key) {
        return ((PyInteger)map.get(Py.newString(key))).getValue();
    }

    public boolean getBoolean (String key) {
        return ((PyBoolean)map.get(Py.newString(key))).getBooleanValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getVariableValue(String key) {
        return (T)(map.get(Py.newString(key)));
    }
    
}
