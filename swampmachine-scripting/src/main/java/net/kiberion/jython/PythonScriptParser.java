package net.kiberion.jython;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

public class PythonScriptParser {

    private PythonScriptParser() {}
    
    public static PyCode parseString(String scriptBody) {

        try (PythonInterpreter interp = new PythonInterpreter()) {
            // try (BufferedReader reader = Files.newBufferedReader(url,
            // StandardCharsets.UTF_8)) {
            PyCode compiledCode = interp.compile(scriptBody);
            
            return compiledCode;
            // }
            // } catch (IOException e) {
            // }
        }
    }

}
