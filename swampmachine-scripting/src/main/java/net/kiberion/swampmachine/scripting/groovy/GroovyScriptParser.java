package net.kiberion.swampmachine.scripting.groovy;

import java.io.Reader;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.kiberion.swampmachine.scripting.common.AbstractScriptParser;

/**
 * Is thread-safe
 * @author kibertoad
 *
 */
public class GroovyScriptParser extends AbstractScriptParser {

    private Set<String> extensions = ImmutableSet.of("groovy");

    @Override
    protected Set<String> getScriptExtensions() {
        return extensions;
    }

    @Override
    public GroovyScript parseScript(String script) {
        return new GroovyScript (script);
    }

    @Override
    public GroovyScript parseScript(Reader script) {
        return new GroovyScript (script);
    }


}
