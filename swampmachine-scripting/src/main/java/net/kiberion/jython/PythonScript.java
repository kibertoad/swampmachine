package net.kiberion.jython;

import java.util.Map;
import java.util.Map.Entry;

import org.python.core.Py;
import org.python.core.PyCode;
import org.python.core.PyStringMap;
import org.python.util.PythonInterpreter;

public class PythonScript {

    private PyCode compiledScript;
    private PyStringMap localVars;

    public PythonScript() {
    }

    public PythonScript(String string) {
        this (PythonScriptParser.parseString(string));
    }

    public PythonScript(PyCode activeScript) {
        this.compiledScript = activeScript;
    }

    public PyMapWrapper invoke(Map<String, Object> params) {
        localVars = Py.newStringMap();

        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            for (Entry<String, Object> entry : params.entrySet()) {

                interpreter.set(entry.getKey(), entry.getValue());
            }

            this.localVars = (PyStringMap) interpreter.getLocals();
            Py.runCode(compiledScript, getLocalVars(), interpreter.getLocals());
            
            return new PyMapWrapper (localVars);
        }
    }

    public PyStringMap getLocalVars() {
        return localVars;
    }

}
