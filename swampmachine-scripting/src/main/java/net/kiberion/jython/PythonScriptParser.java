package net.kiberion.jython;

import java.nio.file.Path;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

public class PythonScriptParser {

    private PythonScriptParser() {
    }

    public static PyCode parsePath(Path scriptPath) {
        //implement later
        throw new UnsupportedOperationException();
    }
    
    public static PyCode parseString(String scriptBody) {
        try (PythonInterpreter interp = new PythonInterpreter()) {
            PyCode compiledCode = interp.compile(scriptBody);
            return compiledCode;
        }
    }

}
