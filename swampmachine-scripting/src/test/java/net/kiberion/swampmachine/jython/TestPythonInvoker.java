package net.kiberion.swampmachine.jython;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.python.core.Py;
import org.python.core.PyInteger;
import org.python.core.PyStringMap;

import net.kiberion.swampmachine.scripting.entities.TestCaster;
import net.kiberion.utils.FilePathUtils;
import net.kiberion.utils.StopWatch;


public class TestPythonInvoker {

    
    @Test
    public void testInvoker () {
        PythonInvoker invoker = new PythonInvoker();
        
        invoker.init();
        List<PythonScript> scripts = invoker.parseScriptsFromPath(FilePathUtils.getResourceRootPath(TestPythonInvoker.class, "test.py"));
        assertEquals (1, scripts.size());
        
        TestCaster caster = new TestCaster();
        assertEquals (0, caster.getSaidMoo());
        assertEquals (0, caster.getMutableNumber().intValue());
        
        PythonBinding params = new PythonBinding();
        params.put("x", 1);
        params.put("caster", caster);
        
        PythonScript script = scripts.get(0);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        PyMapWrapper result = script.invoke(params);
        stopWatch.endAndLog("Python"); //Measure script execution time
        
        PyStringMap localVars = script.getLocalVars();
        assertNotNull (localVars);
        assertEquals (2, ((PyInteger)localVars.get(Py.newString("x"))).getValue());
        
        assertEquals (1, caster.getSaidMoo());
        assertEquals (1, caster.getMutableNumber().intValue());
        
        assertEquals (true, result.getBoolean("result"));
    }
}
