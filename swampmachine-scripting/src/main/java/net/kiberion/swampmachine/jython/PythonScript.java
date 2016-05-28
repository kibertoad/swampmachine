package net.kiberion.swampmachine.jython;

import java.util.Map.Entry;

import org.python.core.Py;
import org.python.core.PyCode;
import org.python.core.PyStringMap;
import org.python.util.PythonInterpreter;

import lombok.Getter;
import net.kiberion.swampmachine.scripting.SwampScript;

public class PythonScript implements SwampScript<PythonBinding, PyMapWrapper>{

    private PyCode compiledScript;

    @Getter
    private PyStringMap localVars;

    public PythonScript() {
    }

    public PythonScript(String string) {
        this(PythonScriptParser.parseString(string));
    }

    public PythonScript(PyCode activeScript) {
        this.compiledScript = activeScript;
    }

    @Override
    public PyMapWrapper invoke(PythonBinding params) {
        localVars = Py.newStringMap();

        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            for (Entry<String, Object> entry : params.entrySet()) {
                interpreter.set(entry.getKey(), entry.getValue());
            }

            this.localVars = (PyStringMap) interpreter.getLocals();
            Py.runCode(compiledScript, getLocalVars(), interpreter.getLocals());

            return new PyMapWrapper(localVars);
        }
    }

}
