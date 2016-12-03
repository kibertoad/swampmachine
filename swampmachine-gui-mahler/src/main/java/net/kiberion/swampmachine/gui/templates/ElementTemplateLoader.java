package net.kiberion.swampmachine.gui.templates;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;

import net.kiberion.swampmachine.assets.util.LoadBeforeStartup;
import net.kiberion.swampmachine.loaders.AbstractJsonLoader;

@LoadBeforeStartup
public class ElementTemplateLoader extends AbstractJsonLoader<ElementTemplate> {

    private static final Logger log = LogManager.getLogger();

    private static final String LOAD_DIRECTORY = "view-compositions/templates";

    @Autowired
    private ElementTemplateRegistry compositionRegistry;

    public ElementTemplateLoader() {
    }
    
    public ElementTemplateLoader(int priority) {
        this.setPriority(priority);
    }    
    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, ElementTemplate> getTargetMap() {
        return compositionRegistry.getElementTemplates();
    }

    @Override
    public String getLoadDirectory() {
        return LOAD_DIRECTORY;
    }

    @Override
    public Class<ElementTemplate> getEntityClass() {
        return ElementTemplate.class;
    }
    
    @Override
    protected TypeReference<List<ElementTemplate>> getTypeReference() {
        return new TypeReference<List<ElementTemplate>>(){};
    }    

}
