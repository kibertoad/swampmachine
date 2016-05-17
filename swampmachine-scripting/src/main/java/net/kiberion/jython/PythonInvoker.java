package net.kiberion.jython;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.python.core.Py;
import org.python.core.PyCode;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.google.common.collect.ImmutableSet;
import org.python.util.PythonInterpreter;

import net.kiberion.swampmachine.utils.FilePathUtils;

public class PythonInvoker {

    private List<Path> scripts;
    private List<PythonScript> compiledScripts;

    private PythonScript activeScript;

    public void init() {
        Properties props = System.getProperties();
        props.setProperty("python.console.encoding", "UTF-8");        

        PySystemState sys = Py.getSystemState();
        sys.path.append(new PyString("C:/software/jython/Lib")); //ToDo parametrize later. Path to Jython (NOT Python) lib folder should be specified here
        
        compiledScripts = new ArrayList<>();
        
        try (PythonInterpreter interp = new PythonInterpreter()) {

            
            for (Path url : scripts) {
                try (BufferedReader reader = Files.newBufferedReader(url, StandardCharsets.UTF_8)) {
                    PyCode compiledCode = interp.compile(reader);

                    /*
                    if (cachedScripts != null) {
                        cachedScripts.putToCache(url.getFileName().toString(), compiledCode);
                    } else {*/
                        activeScript = new PythonScript (compiledCode);
                        compiledScripts.add(activeScript);
                    //}
                } catch (IOException e) {
                }

            }
        }
    }

    public void scanPathForScript(Path directory, String extension) {
        try {
            scripts = FilePathUtils.getListOfFilesByExtension(directory, ImmutableSet.of(extension));
        } catch (IOException e) {
        }
    }

    protected List<Path> getScripts() {
        return scripts;
    }
    
    public PyMapWrapper invoke (Map<String, Object> params){
        //PyStringMap locals=Py.newStringMap();
        //Py.runCode(activeScript, locals, locals);
        return activeScript.invoke(params);
    }
    
    public PythonScript getActiveScript() {
        return activeScript;
    }

}
