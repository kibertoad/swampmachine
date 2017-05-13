package net.kiberion.swampmachine.assets.loaders.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.kiberion.swampmachine.utils.SetUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import net.kiberion.swampmachine.assets.loaders.api.EntityYamlLoader;
import net.kiberion.swampmachine.assets.loaders.util.FileReaderFactory;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;

/**
 * This loader is used for deserializing entities from YAML files directly into
 * POJO entities. Note that structure of source YAML data and target class
 * setters should match, otherwise exception will be thrown
 * 
 * @author kibertoad
 */
public class POJOYamlLoader<T extends EntityModelDescriptor> implements EntityYamlLoader<T> {

    private static final Logger log = LogManager.getLogger();
    private AbstractFileReader fileReader;

    @Override
    public void setFileReader(AbstractFileReader fileReader) {
        this.fileReader = fileReader;
    }

    // if empty, only load single file
    public Set<String> wildcardFileExtension = new HashSet<>();
    public Path path;
    public Class<?> entityClass;

    public POJOYamlLoader(Path path, Class<?> clazz) {
        this.path = path;
        this.entityClass = clazz;
        this.fileReader = FileReaderFactory.buildFileReader(path);
    }

    public POJOYamlLoader(Path path, Class<?> clazz, String... wildcardFileExtension) {
        this(path, clazz);
        setSupportedFileExtensions(wildcardFileExtension);
    }

    @SuppressWarnings("unchecked")
    private List<T> loadFile(Path path) throws IOException {
        log.info("Loading YAML file: " + path.toString());
        List<T> list = new ArrayList<>();
        Yaml yaml = new Yaml(new Constructor(entityClass));

        try (InputStream is = fileReader.getFileAsStream(path)) {
            for (Object asObj : yaml.loadAll(is)) {
                list.add((T) asObj);
            }
        }
        return list;
    }

    @Override
    public Map<String, T> loadMap() throws IOException {
        Map<String, T> results = new HashMap<>();

        // Parse one file
        if (wildcardFileExtension.isEmpty()) {
            List<T> loadedEntities = loadFile(path);
            for (T loadedEntity : loadedEntities) {
                results.put(loadedEntity.getId(), loadedEntity);
            }

        }

        // Parse whole directory, filtered by extension
        else {
            List<Path> filesToLoad = fileReader.getListOfFilesByWildcard(path, wildcardFileExtension);
            for (Path entry : filesToLoad) {
                List<T> loadedEntities = loadFile(entry);
                for (T loadedEntity : loadedEntities) {
                    results.put(loadedEntity.getId(), loadedEntity);
                }
            }
        }
        return results;
    }

    @Override
    public POJOYamlLoader<T> setSupportedFileExtensions(String... wildcards) {
        wildcardFileExtension = SetUtils.buildSet(wildcards);
        return this;
    }
}
