package net.kiberion.swampmachine.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.composer.Composition;
import net.kiberion.swampmachine.gui.composer.CompositionLoader;
import net.kiberion.swampmachine.gui.composer.CompositionRegistry;
import net.kiberion.swampmachine.gui.composition.elements.CompositionElement;
import net.kiberion.swampmachine.gui.spring.MahlerContextBasedTest;
import net.kiberion.swampmachine.gui.templates.ElementTemplateLoader;

public class CompositionLoaderTest extends MahlerContextBasedTest{

    @Autowired
    private CompositionLoader loader;
    
    @Autowired
    private ElementTemplateLoader templateLoader;
    
    @Autowired
    private CompositionRegistry registry;
    
    @Test
    public void testLoadFile () {
        templateLoader.load();
        loader.load();
        assertEquals(1, registry.getCompositions().size());
        Composition composition = registry.getCompositions().get("test");
        assertEquals ("test", composition.getId());
        
        assertEquals (3, composition.getElementMap().size());
        
        CompositionElement compositionFromTemplate = composition.getElementMap().get("statElement");
        assertNotNull (compositionFromTemplate);
        assertEquals ("swPlusMinusComposition", compositionFromTemplate.getType());
        assertEquals ("controller.getStat('intelligence')", compositionFromTemplate.getProperties().get("labelValue"));
    }
    
}
