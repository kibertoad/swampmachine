package net.kiberion.swampmachine.loaders;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.assets.util.LoadOnStartup;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourceDescriptor;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.registries.StaticModelInfoRegistry;

@LoadOnStartup
public class ResourcesLoader extends AbstractLoader{

    @Autowired
    private CommonModelInfoRegistry modelRegistry;
    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, ResourceDescriptor> getTargetMap() {
        return modelRegistry.getResources();
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

    
    @Override
    public void load() {
        super.load();
        modelRegistry.setExistingResources(modelRegistry.getResources().keySet());
        StaticModelInfoRegistry.setExistingResources(modelRegistry.getResources().keySet());
    }
    
}
