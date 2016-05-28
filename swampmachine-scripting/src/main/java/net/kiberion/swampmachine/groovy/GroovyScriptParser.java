package net.kiberion.swampmachine.groovy;

import java.io.Reader;
import java.util.Set;

import org.python.google.common.collect.ImmutableSet;

import net.kiberion.swampmachine.scripting.AbstractScriptParser;
import net.kiberion.swampmachine.scripting.SwampScript;

public class GroovyScriptParser extends AbstractScriptParser {

    private Set<String> extensions = ImmutableSet.of("groovy");

    @Override
    protected Set<String> getScriptExtensions() {
        return extensions;
    }

    @Override
    protected SwampScript parseScript(String script) {
        return new GroovyScript (script);
    }

    @Override
    protected SwampScript parseScript(Reader script) {
        return new GroovyScript (script);
    }


}
