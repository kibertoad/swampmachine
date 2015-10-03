package net.kiberion.jython;

import org.python.core.Py;
import org.python.core.PyBoolean;
import org.python.core.PyInteger;
import org.python.core.PyStringMap;

public class PyMapWrapper {

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
    
    
}
