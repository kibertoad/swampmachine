package net.kiberion.swampmachine.blueprints.common.loaders;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.blueprints.common.entities.impl.CreatureModelInfo;
import net.kiberion.swampmachine.loaders.CommonSyncStartupLoader;

public class CreatureLoader extends CommonSyncStartupLoader {

    @Autowired
    private CreatureRegistry creatureRegistry;

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
        return creatureRegistry.getCreatures();
    }
    
    @Override
    public String getLoadFileExtension() {
        return CREATURE_ENTITIES_FILE_EXTENSION;
    }
    
    

}
