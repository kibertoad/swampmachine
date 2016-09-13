package net.kiberion.swampmachine.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.composer.CompositionLoader;
import net.kiberion.swampmachine.gui.composer.CompositionTree;

public class CompositionLoaderTest {

    private static final Resource TEST_COMPOSITION_RESOURCE = new ClassPathResource("TestMenu.json"); 
    
    @Test
    public void testLoadFile () {
        CompositionLoader loader = new CompositionLoader();
        CompositionTree compositionTree = loader.load(TEST_COMPOSITION_RESOURCE);
        assertNotNull (compositionTree);
        assertEquals(1, compositionTree.getCompositions().size());
        Composition composition = compositionTree.getCompositions().get(0);
        assertEquals ("test", composition.getId());
        
        assertEquals (1, composition.getElements().size());
    }
    
}
