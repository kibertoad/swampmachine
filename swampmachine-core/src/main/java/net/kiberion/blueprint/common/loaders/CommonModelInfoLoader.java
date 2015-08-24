package net.kiberion.blueprint.common.loaders;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.kiberion.assets.loaders.AssetLoader;
import net.kiberion.assets.loaders.POJOLoader;
import net.kiberion.assets.readers.ReaderHelper;
import net.kiberion.blueprint.common.registries.CommonModelInfoRegistry;
import net.kiberion.entities.modelinfo.CreatureModelInfo;
import net.kiberion.utils.MapUtils;

@Singleton
public class CommonModelInfoLoader implements AssetLoader {

    private static final Logger log = LogManager.getLogger();

    @Inject
    private CommonModelInfoRegistry modelInfoRegistry;

    @Inject
    private ReaderHelper readerHelper;

    public CommonModelInfoLoader() {
        readerHelper = new ReaderHelper();
    }

    protected void loadCreatureModelInfo() {

        try {
            if (fileExists("model-creature/")) {
                
                log.info("Loading creatures from: "+getPathToAssets().resolve("model-creature/").toString());
                
                POJOLoader<CreatureModelInfo> creatureLoader = new POJOLoader<>(
                        getPathToAssets().resolve("model-creature/"), CreatureModelInfo.class, "creatures");

                List<CreatureModelInfo> creatures = creatureLoader.loadList();

                MapUtils.putAll(modelInfoRegistry.getCreatures(), creatures);

                log.info("Done loading stuff.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
    }

    private Path getPathToAssets() {
        return readerHelper.getPathToAssets();
    }

    private boolean fileExists(String directoryName) {
        return readerHelper.fileExists(directoryName);
    }

    @Override
    public void load() {
        loadCreatureModelInfo();
    }

}
