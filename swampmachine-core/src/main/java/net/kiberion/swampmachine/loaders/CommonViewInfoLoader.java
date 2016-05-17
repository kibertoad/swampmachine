package net.kiberion.swampmachine.loaders;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.assets.loaders.impl.EntityViewInfoLoader;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;
import net.kiberion.swampmachine.assets.viewinfo.EntityViewInfo;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.registries.CommonViewInfoRegistry;
import net.kiberion.swampmachine.utils.MapUtils;

@LoadOnStartup
public class CommonViewInfoLoader implements SyncAssetLoader{

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private CommonViewInfoRegistry viewInfoRegistry;
    
    @Autowired
    private CommonModelInfoRegistry modelInfoRegistry;

    @Autowired
    private ReaderHelper readerHelper;
    
    @Autowired
    private GameConfig config;
    
    @Setter
    private boolean imagesAreMandatory = true;
    
    // convention over configuration in this case - if you are using
    // Swampmachine asset management, you are expected to follow path convention
    private static final String CREATURE_MODEL_DIRECTORY = "model-creature";
    private static final String CREATURE_ENTITIES_FILE_EXTENSION = "creatures";
    
    
    @Override
    public int getPriority() {
        return 100;
    }
    
    public void loadCreatureViewInfoFromModel(Map <String, CreatureModelInfo> fullCreatureList) {
        
        if (readerHelper.fileExists(CREATURE_MODEL_DIRECTORY)) {
            Objects.requireNonNull(fullCreatureList, "Creatures are mandatory");
            log.info("debug", "Start loading creature images");
            Path entityDirectory = config.getPathToResources().resolve(CREATURE_MODEL_DIRECTORY);
            EntityViewInfoLoader loader = new EntityViewInfoLoader(entityDirectory.toString()+"*", fullCreatureList, CREATURE_ENTITIES_FILE_EXTENSION);
            loader.setImageIsMandatory(imagesAreMandatory);
            Map<String, EntityViewInfo> creatureViewInfo = loader.loadMap();
            MapUtils.putAllEntities(viewInfoRegistry.getFullCreatureViewInfoList(), creatureViewInfo);

            /*
            try {
                this.creatureMovementAnimation = new POJOLoader<DirectionalAnimationList>(getPathToAssets().resolve("view-creature/"), DirectionalAnimationList.class, "view").setWildcardFileExtension("creatures").loadToMap();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            
            log.info("Done loading creature images");
        }
    }

    @Override
    public void load() {
        loadCreatureViewInfoFromModel(modelInfoRegistry.getCreatures());
    }
    
}
