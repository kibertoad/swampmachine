package net.kiberion.swampmachine.loaders;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.utils.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;
import net.kiberion.swampmachine.assets.loaders.impl.POJOYamlLoader;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;

/**
 * Synchronous loader that fills target map with data from source.
 * Is supposed to get all necessary configuration during construction.
 * Actual loading is invoked via no-args method
 *
 * @author kibertoad
 */
public abstract class AbstractLoader implements SyncLoader {

    private static final Logger log = LogManager.getLogger();

    //Lower number means higher priority
    @Setter
    @Getter
    private int priority = 100;

    @Autowired
    @Getter
    private final ReaderHelper readerHelper;

    @Autowired
    private GameConfig config;

    public AbstractLoader() {
        readerHelper = new ReaderHelper();
    }

    protected boolean fileExists(String directoryName) {
        return readerHelper.fileExists(directoryName);
    }

    public abstract <T extends EntityModelDescriptor> Map<String, T> getTargetMap();

    public abstract String getLoadDirectory();

    public abstract String getLoadFileExtension();

    public abstract Class<? extends EntityModelDescriptor> getEntityClass();

    @Override
    public void load() {
        loadDataNodes(getTargetMap(), getLoadDirectory(), getLoadFileExtension(), getEntityClass());
    }

    protected Path getEntityDirectory(String loadDirectory) {
        return config.getPathToResources().resolve(loadDirectory);
    }

    protected <T extends EntityModelDescriptor> void loadDataNodes(Map<String, T> targetMap, String loadDirectory,
                                                                   String loadExtension, Class<T> clazz) {
        try {
            if (fileExists(loadDirectory)) {
                Path entityDirectory = getEntityDirectory(loadDirectory);
                log.info("Loading entities from: " + entityDirectory.toString());
                POJOYamlLoader<T> entityLoader = new POJOYamlLoader<>(entityDirectory, clazz, loadExtension);
                List<T> entities = entityLoader.loadList();

                MapUtils.putAllEntities(targetMap, entities);
                log.info("Loaded " + entities.size() + " " + getEntityClass().getSimpleName() + " entities.");
            } else {
                log.warn("Loading directory not found: " + loadDirectory);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
    }

    protected Logger getLog() {
        return log;
    }

}
