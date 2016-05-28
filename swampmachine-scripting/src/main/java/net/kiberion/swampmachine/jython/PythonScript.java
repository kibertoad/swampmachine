package net.kiberion.swampmachine.jython;

import java.util.Map.Entry;

import org.python.core.Py;
import org.python.core.PyCode;
import org.python.core.PyStringMap;
import org.python.util.PythonInterpreter;

import net.kiberion.swampmachine.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.SwampScript;
import net.kiberion.swampmachine.scripting.SwampScriptInvokationResult;

public class PythonScript implements SwampScript {

    private final PyCode compiledScript;

    public PythonScript(String scriptBody) {
        try (PythonInterpreter interp = new PythonInterpreter()) {
            this.compiledScript = interp.compile(scriptBody);
        }
    }

    public PythonScript(PyCode script) {
        this.compiledScript = script;
    }

    @Override
    public SwampScriptInvokationResult invoke(SwampBinding params) {
        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            for (Entry<String, Object> entry : params.getVariableEntries()) {
                interpreter.set(entry.getKey(), entry.getValue());
            }

            final PyStringMap localVars = (PyStringMap) interpreter.getLocals();
            Py.runCode(compiledScript, localVars, interpreter.getLocals());

            return new PyMapWrapper(localVars);
        }
    }

}
