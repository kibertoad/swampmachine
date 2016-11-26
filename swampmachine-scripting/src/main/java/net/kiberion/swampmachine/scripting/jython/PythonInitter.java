package net.kiberion.swampmachine.scripting.jython;

import java.util.Properties;

/**
 * Call init in this class before calling any python logic
 * @author kibertoad
 *
 */
public class PythonInitter {

    public static void init () {
        Properties props = System.getProperties();
        props.setProperty("python.console.encoding", "UTF-8");        

        // Unnecessary when using jython-standalone
        //PySystemState sys = Py.getSystemState();
        //sys.path.append(new PyString("C:/software/jython/Lib")); //ToDo parametrize later. Path to Jython (NOT Python) lib folder should be specified here
    }
    
}
