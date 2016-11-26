package net.kiberion.swampmachine.scripting.jruby;

import java.io.Reader;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import javax.script.CompiledScript;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jruby.embed.LocalContextScope;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.jsr223.JRubyEngine;
import org.python.google.common.collect.ImmutableSet;

import net.kiberion.swampmachine.api.scripting.SwampScript;
import net.kiberion.swampmachine.scripting.common.AbstractScriptParser;

/**
 *
 * Note that you need to have JRuby installed in your system in order for this
 * to work. ToDo look into somehow bundling JRuby
 * 
 * @author kibertoad
 *
 */
public class RubyScriptParser extends AbstractScriptParser {

    private JRubyEngine engine = (JRubyEngine) new ScriptEngineManager().getEngineByName("jruby");

    private boolean isInitted;

    private Set<String> extensions = ImmutableSet.of("rb");

    @Override
    public RubyScript parseScript(Reader reader) {
        try {
            CompiledScript compiledScript = engine.compile(reader);
            return new RubyScript(compiledScript);
        } catch (ScriptException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public RubyScript parseScript(String script) {
        try {
            CompiledScript compiledScript = engine.compile(script);
            return new RubyScript(compiledScript);
        } catch (ScriptException e) {
            throw new IllegalStateException(e);
        }
    }

    public void init() {
        System.setProperty("org.jruby.embed.localcontext.scope", LocalContextScope.THREADSAFE.name().toLowerCase());
        System.setProperty("org.jruby.embed.localvariable.behavior",
                LocalVariableBehavior.TRANSIENT.name().toLowerCase());
        isInitted = true;
    }

    @Override
    public List<SwampScript> parseScriptsFromPath(Path directory) {
        if (!isInitted) {
            init();
        }
        return super.parseScriptsFromPath(directory);
    }

    @Override
    protected Set<String> getScriptExtensions() {
        return extensions;
    }

}
