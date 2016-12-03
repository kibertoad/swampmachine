package net.kiberion.swampmachine.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.gui.spring.MahlerContextBasedTest;
import net.kiberion.swampmachine.gui.templates.ElementTemplate;
import net.kiberion.swampmachine.gui.templates.ElementTemplateLoader;
import net.kiberion.swampmachine.gui.templates.ElementTemplateRegistry;

public class ElementTemplateLoaderTest extends MahlerContextBasedTest{

    @Autowired
    private ElementTemplateLoader loader;
    
    @Autowired
    private ElementTemplateRegistry registry;
    
    @Test
    public void testLoadFile () {
        loader.load();
        assertEquals(1, registry.getElementTemplates().size());
        ElementTemplate template = registry.getElementTemplates().get("plusMinusTemplate");
        assertEquals ("plusMinusTemplate", template.getId());
        
        //assertEquals (2, template.getElementMap().size());
    }
    
}
