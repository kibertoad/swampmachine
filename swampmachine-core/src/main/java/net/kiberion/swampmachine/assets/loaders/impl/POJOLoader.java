package net.kiberion.swampmachine.assets.loaders.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import net.kiberion.swampmachine.assets.loaders.api.AbstractLoader;
import net.kiberion.swampmachine.assets.loaders.util.FileReaderFactory;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.utils.MapUtils;
import net.kiberion.swampmachine.utils.SetUtils;

/**
 * @author kibertoad
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class POJOLoader<T extends EntityModelDescriptor> implements AbstractLoader<T> {

    private static final Logger log = LogManager.getLogger();

    private AbstractFileReader fileReader;

    @Override
    public void setFileReader(AbstractFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public Set<String> wildcardFileExtension = SetUtils.buildSet(); // if empty, only load single file
    public Path path;
    public Class clazz;

    public POJOLoader(Path path, Class clazz) {
        this.path = path;
        this.clazz = clazz;
        this.fileReader = FileReaderFactory.buildFileReader(path);
    }

    public POJOLoader(Path path, Class clazz, String... wildcardFileExtension) {
        this(path, clazz);
        setWildcardFileExtension(wildcardFileExtension);
    }

    private List<T> loadFile(Path path) throws IOException {
        log.info("Loading YAML file: " + path.toString());

        List<T> list = new ArrayList<>();
        Yaml yaml = new Yaml(new Constructor(clazz));

        for (Object asObj : yaml.loadAll(fileReader.getFileAsStream(path))) {
            list.add((T) asObj);
        }
        return list;
    }

    @Override
    public Map<String, T> load() throws IOException {

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

    public Map<String, T> loadToMap() throws IOException {
        Map<String, T> result = new HashMap<>();

        // Parse one file
        if (wildcardFileExtension.isEmpty()) {
            List<T> loadedEntries = loadFile(path);
            for (T entry : loadedEntries) {
                result.put(entry.getId(), entry);
            }
            
        } 
        
        // Parse whole directory, filtered by extension
        else {
            List<Path> filesToLoad = fileReader.getListOfFilesByWildcard(path, wildcardFileExtension);
            for (Path fileEntry : filesToLoad) {
                List<T> loadedEntries = loadFile(fileEntry);
                MapUtils.putAllEntities(result, loadedEntries);
            }
        }
        return result;
    }

    @Override
    public List<T> loadList() throws IOException {
        return new ArrayList<>(loadToMap().values());
    }

    @Override
    public POJOLoader<T> setWildcardFileExtension(String... wildcards) {
        wildcardFileExtension = SetUtils.buildSet(wildcards);
        return this;
    }
}
