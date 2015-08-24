package net.kiberion.blueprint.common.loaders;

import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.kiberion.assets.loaders.AssetLoader;
import net.kiberion.assets.loaders.EntityViewInfoLoader;
import net.kiberion.assets.readers.ReaderHelper;
import net.kiberion.assets.viewinfo.CreatureViewInfo;
import net.kiberion.blueprint.common.registries.CommonModelInfoRegistry;
import net.kiberion.blueprint.common.registries.CommonViewInfoRegistry;
import net.kiberion.entities.modelinfo.CreatureModelInfo;
import net.kiberion.utils.MapUtils;

@Singleton
public class CommonViewInfoLoader implements AssetLoader{

    private static final Logger log = LogManager.getLogger();

    @Inject
    private CommonViewInfoRegistry viewInfoRegistry;
    
    @Inject
    private CommonModelInfoRegistry modelInfoRegistry;

    @Inject
    private ReaderHelper readerHelper;
    
    public void loadCreatureViewInfoFromModel(Map <String, CreatureModelInfo> fullCreatureList) {
        
        if (readerHelper.fileExists("model-creature/")) {
            Objects.requireNonNull(fullCreatureList, "Creatures are mandatory");
            log.info("debug", "Start loading creature images");
            EntityViewInfoLoader loader = new EntityViewInfoLoader(readerHelper.getPathToAssets().resolve("model-creature/").toString()+"*", fullCreatureList, "creatures");
            Map<String, CreatureViewInfo> creatureViewInfo = loader.load();
            MapUtils.putAll(viewInfoRegistry.getFullCreatureViewInfoList(), creatureViewInfo);

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
