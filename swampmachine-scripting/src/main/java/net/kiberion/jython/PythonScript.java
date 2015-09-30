package net.kiberion.jython;

import java.util.Map;
import java.util.Map.Entry;

import org.python.core.Py;
import org.python.core.PyCode;
import org.python.core.PyObject;
import org.python.core.PyStringMap;
import org.python.util.PythonInterpreter;

public class PythonScript {

    private PyCode compiledScript;
    private PyStringMap localVars;
    private PyStringMap globalVars;

    public PythonScript(PyCode activeScript) {
        this.compiledScript = activeScript;
    }

    public PyObject invoke(Map<String, Object> params) {
        localVars = Py.newStringMap();
        globalVars = Py.newStringMap();

        
        PythonInterpreter interpreter = new PythonInterpreter();



        
        for (Entry<String, Object> entry : params.entrySet()) {

            /*
            if (entry.getValue() instanceof Integer) {
                localVars.getMap().put(Py.newString(entry.getKey()), Py.newInteger((Integer) entry.getValue()));
            }
            if (entry.getValue() instanceof String) {
                localVars.getMap().put(Py.newString(entry.getKey()), Py.newString((String) entry.getValue()));
            }
            */
            
            interpreter.set(entry.getKey(), entry.getValue());
        }

        this.localVars = (PyStringMap) interpreter.getLocals();
        return Py.runCode(compiledScript, getLocalVars(), interpreter.getLocals());
    }

    public PyStringMap getLocalVars() {
        return localVars;
    }

    public PyStringMap getGlobalVars() {
        return globalVars;
    }

}
