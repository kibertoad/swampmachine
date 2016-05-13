package net.kiberion.swampmachine.blueprint.common.loaders;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import net.kiberion.swampmachine.assets.loaders.api.POJOLoader;
import net.kiberion.swampmachine.assets.readers.ReaderHelper;
import net.kiberion.swampmachine.entities.common.impl.DataNode;
import net.kiberion.swampmachine.utils.MapUtils;

public class AbstractLoader {

    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    @Getter
    private final ReaderHelper readerHelper;

    public AbstractLoader() {
        readerHelper = new ReaderHelper();
    }

    protected Path getPathToAssets() {
        return readerHelper.getPathToAssets();
    }

    protected boolean fileExists(String directoryName) {
        return readerHelper.fileExists(directoryName);
    }
    
    
    protected <T extends DataNode> void loadDataNodes (Map<String, T> targetMap, String loadDirectory, String loadExtension, Class<T> clazz) {
        try {
            if (fileExists(loadDirectory)) {
                log.info("Loading torsos from: " + getPathToAssets().resolve(loadDirectory).toString());

                POJOLoader<T> entityLoader = new POJOLoader<>(getPathToAssets().resolve(loadDirectory),
                        clazz, loadExtension);
                List<T> entities = entityLoader.loadList();

                MapUtils.putAll(targetMap, entities);
                log.info("Done loading stuff.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
        
    }

}
