package net.kiberion.swampmachine.groovy;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.python.google.common.collect.ImmutableSet;

import lombok.Getter;
import net.kiberion.swampmachine.scripting.AbstractScriptParser;

public class GroovyInvoker extends AbstractScriptParser<GroovyScript> {

    private static final Logger log = LogManager.getLogger();

    private Set<String> extensions = ImmutableSet.of("groovy");

    @Getter
    private List<GroovyScript> compiledScripts = new ArrayList<>();

    /*
    @Override
    protected GroovyScript parseScript(Reader reader) {
        try (PythonInterpreter interp = new PythonInterpreter()) {
            PyCode compiledCode = interp.compile(reader);
            GroovyScript activeScript = new PythonScript(compiledCode);
            compiledScripts.add(activeScript);
            return activeScript;
        }
    }

    @Override
    protected GroovyScript parseScript(String script) {
        try (PythonInterpreter interp = new PythonInterpreter()) {
            PyCode compiledCode = interp.compile(script);
            GroovyScript activeScript = new PythonScript(compiledCode);
            compiledScripts.add(activeScript);
            return activeScript;
        }
    }

    public void init() {
        PythonInitter.init();
    }
    */

    @Override
    protected Set<String> getScriptExtensions() {
        return extensions;
    }

    @Override
    protected GroovyScript parseScript(String script) {
        return new GroovyScript (script);
    }

    @Override
    protected GroovyScript parseScript(Reader script) {
        return new GroovyScript (script);
    }


}
