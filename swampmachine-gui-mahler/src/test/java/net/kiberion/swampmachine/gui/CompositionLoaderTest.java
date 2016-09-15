package net.kiberion.swampmachine.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.composer.CompositionLoader;
import net.kiberion.swampmachine.gui.composer.CompositionRegistry;
import net.kiberion.swampmachine.gui.spring.MahlerContextBasedTest;

public class CompositionLoaderTest extends MahlerContextBasedTest{

    @Autowired
    private CompositionLoader loader;
    
    @Autowired
    private CompositionRegistry registry;
    
    @Test
    public void testLoadFile () {
        loader.load();
        assertEquals(1, registry.getCompositions().size());
        Composition composition = registry.getCompositions().get("test");
        assertEquals ("test", composition.getId());
        
        assertEquals (1, composition.getElements().size());
    }
    
}
