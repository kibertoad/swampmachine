package net.kiberion.swampmachine.groovy;

import java.io.Reader;
import java.util.Set;

import org.python.google.common.collect.ImmutableSet;

import net.kiberion.swampmachine.scripting.AbstractScriptParser;

public class GroovyScriptParser extends AbstractScriptParser {

    private Set<String> extensions = ImmutableSet.of("groovy");

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
