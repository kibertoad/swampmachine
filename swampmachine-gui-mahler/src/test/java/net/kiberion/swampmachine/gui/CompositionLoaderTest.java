package net.kiberion.swampmachine.gui;

import static org.junit.Assert.*;

import java.util.Map;

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
    
    @SuppressWarnings("unchecked")
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
        assertEquals ("Intelligence", compositionFromTemplate.getProperties().get("labelText"));
        Map<String, Object> plusMap = (Map<String, Object>) compositionFromTemplate.getProperties().get("plus");
        assertEquals ("controller.incrementStat('intelligence')", plusMap.get("onClickScript"));
        assertNotNull (compositionFromTemplate.getPosition());
        assertEquals (710, compositionFromTemplate.getPosition().getIntX());
    }
    
}
