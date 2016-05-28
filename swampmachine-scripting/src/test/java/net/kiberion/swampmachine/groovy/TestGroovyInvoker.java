package net.kiberion.swampmachine.groovy;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import net.kiberion.swampmachine.scripting.entities.TestCaster;
import net.kiberion.utils.FilePathUtils;
import net.kiberion.utils.StopWatch;

public class TestGroovyInvoker {

    @Test
    public void testInvoker () {
        GroovyInvoker invoker = new GroovyInvoker();
        
        List<GroovyScript> scripts = invoker.parseScriptsFromPath(FilePathUtils.getResourceRootPath(TestGroovyInvoker.class, "test.groovy"));
        assertEquals (1, scripts.size());
        
        TestCaster caster = new TestCaster();
        assertEquals (0, caster.getSaidMoo());
        assertEquals (0, caster.getMutableNumber().intValue());
        
        GroovyBinding params = new GroovyBinding();
        params.setVariable("x", 1);
        params.setVariable("caster", caster);
        
        GroovyScript script = scripts.get(0);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        GroovyInvokationResult result = script.invoke(params);
        stopWatch.endAndLog("Groovy"); //Measure script execution time
        
        assertNotNull (result);
        assertEquals (Integer.valueOf(2), result.getVariableValue("x"));
        
        assertEquals (1, caster.getSaidMoo());
        assertEquals (1, caster.getMutableNumber().intValue());
        
        assertEquals (true, result.getVariableValue("result"));
    }    
}
