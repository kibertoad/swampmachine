package net.kiberion.swampmachine.blueprints.common.loaders;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;
import net.kiberion.swampmachine.assets.loaders.impl.EntityViewInfoLoader;
import net.kiberion.swampmachine.assets.loaders.impl.EntityViewInfoLoader.ImageModelEntityDescriptor;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;
import net.kiberion.swampmachine.blueprints.common.entities.impl.CreatureModelInfo;
import net.kiberion.swampmachine.blueprints.common.registries.CreatureRegistry;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.registries.CommonViewInfoRegistry;

@LoadOnStartup
public class CommonViewInfoLoader implements SyncLoader{

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private CommonViewInfoRegistry viewInfoRegistry;
    
    @Autowired
    private CommonModelInfoRegistry modelInfoRegistry;

    @Autowired
    private ReaderHelper readerHelper;
    
    @Autowired
    private GameConfig config;
    
    @Autowired
    private CreatureRegistry creatureRegistry;
    
    @Setter
    private boolean imagesAreMandatory = false;
    
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
            EntityViewInfoLoader loader = new EntityViewInfoLoader(entityDirectory.toString()+"*", CREATURE_ENTITIES_FILE_EXTENSION);
            loader.setImageIsMandatory(imagesAreMandatory);
            Map<String, EntityViewInfoLoader.ImageModelEntityDescriptor> creatureViewInfo = loader.loadMap();
            
            for (ImageModelEntityDescriptor entry : creatureViewInfo.values()) {
                //if (entry.getImageId() == null) {
                //    entry.setImageId(UiManager.BLANK_IMAGE);
                //}
                
                viewInfoRegistry.getEntityViewMap().put(entry.getId(), entry.getImageId());
            }
            

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
        loadCreatureViewInfoFromModel(creatureRegistry.getCreatures());
    }
    
}
