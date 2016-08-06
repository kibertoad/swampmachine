package net.kiberion.swampmachine.loaders;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourceDescriptor;
import net.kiberion.swampmachine.registries.ResourceRegistry;
import net.kiberion.swampmachine.registries.StaticModelInfoRegistry;

/**
 * This loader automatically loads all the resources at the init
 * @author kibertoad
 *
 */
public class ResourcesLoader extends AbstractLoader{

    @Autowired
    private ResourceRegistry resourcesRegistry;
    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, ResourceDescriptor> getTargetMap() {
        return resourcesRegistry.getResources();
    }
    
    @Override
    public String getLoadDirectory() {
        return "model-resources";
    }

    @Override
    public String getLoadFileExtension() {
        return "resources";
    }

    @Override
    public Class<? extends EntityModelDescriptor> getEntityClass() {
        return ResourceDescriptor.class;
    }

    @PostConstruct
    @Override
    public void load() {
        super.load();
        resourcesRegistry.setExistingResources(resourcesRegistry.getResources().keySet());
        StaticModelInfoRegistry.setExistingResources(resourcesRegistry.getResources().keySet());
    }
    
}
