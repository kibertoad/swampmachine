package net.kiberion.swampmachine.loaders;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import net.kiberion.swampmachine.assets.GameConfig;
import net.kiberion.swampmachine.assets.loaders.api.POJOLoader;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.utils.MapUtils;

/**
 * 
 * 
 * @author kibertoad
 *
 */
public abstract class AbstractLoader {

    private static final Logger log = LogManager.getLogger();

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

    public abstract <T extends CommonModelEntityDescriptor> Map<String, T> getTargetMap ();
    public abstract String getLoadDirectory ();
    public abstract String getLoadFileExtension ();
    public abstract Class<? extends CommonModelEntityDescriptor> getEntityClass();
    
    public void load () {
        loadDataNodes(getTargetMap(), getLoadDirectory(), getLoadFileExtension(), getEntityClass());
    }
    
    protected <T extends CommonModelEntityDescriptor> void loadDataNodes(Map<String, T> targetMap, String loadDirectory,
            String loadExtension, Class<T> clazz) {
        try {
            if (fileExists(loadDirectory)) {
                Path entityDirectory = config.getPathToResources().resolve(loadDirectory);
                log.info("Loading entities from: " + entityDirectory.toString());

                POJOLoader<T> entityLoader = new POJOLoader<>(entityDirectory, clazz, loadExtension);
                List<T> entities = entityLoader.loadList();

                MapUtils.putAllEntities(targetMap, entities);
                log.info("Done loading entities.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }

    }

}
