package net.kiberion.swampmachine.jruby;

import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jruby.embed.jsr223.JRubyEngineFactory;

import net.kiberion.swampmachine.factories.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptTest;
import net.kiberion.utils.FilePathUtils;


public class TestRuby extends AbstractScriptTest{

    
    
    private final RubyEntityFactory factory = new RubyEntityFactory();
    
    @Override
    protected ScriptEntityFactory getEntityFactory() {
        return factory;
    }
    
    
    @Override
    protected Path getPathToTestResourcres() {
        return FilePathUtils.getResourceRootPath(TestRuby.class, "test.rb");
    }
    
    public void simpleTest () {
        
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // make sure we have classloader which does not find jruby
        ClassLoader c = new URLClassLoader( new URL[] {}, null );
        try {
          c.loadClass( "org.jruby.embed.ScriptingContainer" );
          fail( "this classloader shall not find jruby" );
        }
        catch( ClassNotFoundException expected){}
        // set it as context classloader
        Thread.currentThread().setContextClassLoader( c );
        
        ScriptEngineManager m = new ScriptEngineManager();
        m.registerEngineName( "jruby", new JRubyEngineFactory() );
        ScriptEngine jruby = m.getEngineByName("jruby");
        try {
            String result = jruby.eval("$LOAD_PATH" ).toString();
        } catch (ScriptException e1) {
            e1.printStackTrace();
        }        
    }
}
