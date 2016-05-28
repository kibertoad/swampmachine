package net.kiberion.swampmachine.jython;

import java.util.Map.Entry;

import org.python.core.Py;
import org.python.core.PyCode;
import org.python.core.PyStringMap;
import org.python.util.PythonInterpreter;

import lombok.Getter;
import net.kiberion.swampmachine.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.SwampScript;
import net.kiberion.swampmachine.scripting.SwampScriptInvokationResult;

public class PythonScript implements SwampScript {

    private PyCode compiledScript;

    @Getter
    private PyStringMap localVars;

    public PythonScript() {
    }

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
        localVars = Py.newStringMap();

        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            for (Entry<String, Object> entry : params.getVariableEntries()) {
                interpreter.set(entry.getKey(), entry.getValue());
            }

            this.localVars = (PyStringMap) interpreter.getLocals();
            Py.runCode(compiledScript, getLocalVars(), interpreter.getLocals());

            return new PyMapWrapper(localVars);
        }
    }

}
