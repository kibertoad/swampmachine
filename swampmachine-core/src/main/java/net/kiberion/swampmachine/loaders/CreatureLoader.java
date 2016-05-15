package net.kiberion.swampmachine.loaders;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;

@LoadOnStartup
public class CreatureLoader extends CommonLoader implements SyncAssetLoader {

    @Autowired
    private CommonModelInfoRegistry modelInfoRegistry;

    // convention over configuration in this case - if you are using
    // Swampmachine asset management, you are expected to follow path convention
    private static final String CREATURE_MODEL_DIRECTORY = "model-creature";
    private static final String CREATURE_ENTITIES_FILE_EXTENSION = "creatures";
    
    @Override
    public int getPriority() {
        return 50;
    }
    
    @Override
    public Class<CreatureModelInfo> getEntityClass() {
        return CreatureModelInfo.class;
    }
    
    @Override
    public String getLoadDirectory() {
        return CREATURE_MODEL_DIRECTORY;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, CreatureModelInfo> getTargetMap() {
        return modelInfoRegistry.getCreatures();
    }
    
    @Override
    public String getLoadFileExtension() {
        return CREATURE_ENTITIES_FILE_EXTENSION;
    }
    
    

}
