package net.kiberion.swampmachine.loaders;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.loaders.api.POJOLoader;
import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.utils.MapUtils;

@LoadOnStartup
@Component
public class CommonModelInfoLoader extends AbstractLoader implements SyncAssetLoader {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private CommonModelInfoRegistry modelInfoRegistry;

    @Autowired
    private GameConfig config;
    
    // convention over configuration in this case - if you are using
    // Swampmachine asset management, you are expected to follow path convention
    private static final String CREATURE_MODEL_DIRECTORY = "model-creature";
    private static final String CREATURE_ENTITIES_FILE_EXTENSION = "creatures";
    
    @Override
    public int getPriority() {
        return 50;
    }

    protected void loadCreatureModelInfo() {

        try {
            if (fileExists(CREATURE_MODEL_DIRECTORY)) {
                Path entityDirectory = config.getPathToResources().resolve(CREATURE_MODEL_DIRECTORY);
                log.info("Loading creatures from: "+entityDirectory.toString());
                
                POJOLoader<CreatureModelInfo> creatureLoader = new POJOLoader<>(entityDirectory, CreatureModelInfo.class, CREATURE_ENTITIES_FILE_EXTENSION);
                List<CreatureModelInfo> creatures = creatureLoader.loadList();

                MapUtils.putAllEntities(modelInfoRegistry.getCreatures(), creatures);
                log.info("Done loading stuff.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
    }

    @Override
    public void load() {
        loadCreatureModelInfo();
    }

}
