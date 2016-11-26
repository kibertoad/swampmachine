package net.kiberion.swampmachine.scripting.jython;

import java.nio.file.Path;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.scripting.AbstractScriptTest;
import net.kiberion.swampmachine.scripting.jython.PythonEntityFactory;
import net.kiberion.swampmachine.utils.common.FilePathUtils;


public class TestPython extends AbstractScriptTest{

    
    
    private final PythonEntityFactory factory = new PythonEntityFactory();
    
    @Override
    protected ScriptEntityFactory getEntityFactory() {
        return factory;
    }
    
    
    @Override
    protected Path getPathToTestResourcres() {
        return FilePathUtils.getResourceRootPath(TestPython.class, "test.py");
    }
    
}
