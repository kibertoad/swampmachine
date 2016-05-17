package net.kiberion.jython;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.python.core.PyCode;
import org.python.google.common.collect.ImmutableSet;
import org.python.util.PythonInterpreter;

import lombok.Getter;
import net.kiberion.swampmachine.utils.FilePathUtils;

public class PythonInvoker {

    private static final Logger log = LogManager.getLogger();

    @Getter
    private List<Path> scripts;

    private List<PythonScript> compiledScripts;

    @Getter
    private PythonScript activeScript;

    public void init() {
        PythonInitter.init();
        compiledScripts = new ArrayList<>();

        try (PythonInterpreter interp = new PythonInterpreter()) {
            for (Path url : scripts) {
                try (BufferedReader reader = Files.newBufferedReader(url, StandardCharsets.UTF_8)) {
                    PyCode compiledCode = interp.compile(reader);
                    activeScript = new PythonScript(compiledCode);
                    compiledScripts.add(activeScript);
                } catch (IOException e) {
                    log.error("IO Exception: ", e);
                }
            }
        }
    }

    public void scanPathForScript(Path directory, String extension) {
        try {
            scripts = FilePathUtils.getListOfFilesByExtension(directory, ImmutableSet.of(extension));
        } catch (IOException e) {
            log.error("IO Exception: ", e);
        }
    }

    public PyMapWrapper invoke(Map<String, Object> params) {
        return activeScript.invoke(params);
    }

}
