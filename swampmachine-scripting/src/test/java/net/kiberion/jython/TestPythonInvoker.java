package net.kiberion.jython;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.python.core.Py;
import org.python.core.PyInteger;
import org.python.core.PyStringMap;

import net.kiberion.utils.FilePathUtils;
import net.kiberion.utils.StopWatch;


public class TestPythonInvoker {

    
    @Test
    public void testInvoker () {
        
        PythonInvoker invoker = new PythonInvoker();
        
        invoker.scanPathForScript(FilePathUtils.getResourceRootPath(TestPythonInvoker.class, "test.py"), "py");
        invoker.init();
        
        TestCaster caster = new TestCaster();
        assertEquals (0, caster.getSaidMoo());
        assertEquals (0, caster.getMutableNumber().intValue());
        
        Map<String, Object> params = new HashMap<>();
        params.put("x", 1);
        params.put("caster", caster);
        
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        PyMapWrapper result = invoker.invoke(params);
        stopWatch.endAndLog("Python");
        
        
        assertEquals (1, invoker.getScripts().size());
        
        PyStringMap localVars = invoker.getActiveScript().getLocalVars();
        assertNotNull (localVars);
        assertEquals (2, ((PyInteger)localVars.get(Py.newString("x"))).getValue());
        
        assertEquals (1, caster.getSaidMoo());
        assertEquals (1, caster.getMutableNumber().intValue());
        
        assertEquals (true, result.getBoolean("result"));
    }
}
