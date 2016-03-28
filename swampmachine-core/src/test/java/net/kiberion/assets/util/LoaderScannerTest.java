package net.kiberion.assets.util;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

import net.kiberion.assets.readers.SimpleFileReader;
import net.kiberion.blueprint.common.loaders.CommonModelInfoLoader;

public class LoaderScannerTest {

    @LoadOnStartup
    private CommonModelInfoLoader loader = new CommonModelInfoLoader();

    private SimpleFileReader fileReader = new SimpleFileReader(Paths.get("dummy"));
    
    @Test
    public void testScanner () {
        LoaderScanner scanner = new LoaderScanner();
        
        assertEquals (1, scanner.scan(this).size());
        
        loader = null;
        
        try {
            scanner.scan(this);
            fail();
        } catch (IllegalStateException e) {
            //expected
        }
        
    }
    
}
