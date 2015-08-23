package net.kiberion.assets.loaders;

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

import com.badlogic.gdx.Gdx;

import net.kiberion.assets.readers.AbstractFileReader;
import net.kiberion.assets.readers.GDXFileReader;
import net.kiberion.assets.readers.SimpleFileReader;
import net.kiberion.entities.common.impl.EntityModel;
import net.kiberion.utils.SetUtils;

/**
 * @author kibertoad
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class POJOLoader<T extends EntityModel> implements AbstractLoader<T> {
    
    private static final Logger log = LogManager.getLogger();

    private AbstractFileReader fileReader;

    @Override
    public void setFileReader(AbstractFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public Set<String> wildcardFileExtension = SetUtils.buildSet(); // if empty,
                                                                    // only load
                                                                    // single
                                                                    // file
    public Path path;
    public Class clazz;

    public POJOLoader(Path path, Class clazz) {
        this.path = path;
        this.clazz = clazz;

        if (Gdx.app != null) {
            this.fileReader = new GDXFileReader(path);
        } else {
            this.fileReader = new SimpleFileReader(path);
        }
    }

    public POJOLoader(Path path, Class clazz, String... wildcardFileExtension) {
        this(path, clazz);
        setWildcardFileExtension(wildcardFileExtension);
    }

    private List<T> loadFile(Path path) throws IOException {
        log.info("Time to load this: "+path.toString());
        
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
        		results.put (loadedEntity.getId(), loadedEntity);
        	}
            
        } else
        // Parse whole directory
        {
            List<Path> filesToLoad = fileReader.getListOfFilesByWildcard(path, wildcardFileExtension);

            
            for (Path entry : filesToLoad) {
            	
            	List<T> loadedEntities = loadFile(entry); 
            	for (T loadedEntity : loadedEntities) {
            		results.put (loadedEntity.getId(), loadedEntity);
            	}
            	
            }
        }
        return results;
    }    
    
    public Map<String, T> loadToMap() throws IOException {
        Map<String, T> result = new HashMap<>();

        if (wildcardFileExtension.isEmpty()) {
            List<T> loadedEntries = loadFile(path);

            for (T entry : loadedEntries) {
                result.put(entry.getId(), entry);
            }

        } else {
            List<Path> filesToLoad = fileReader.getListOfFilesByWildcard(path, wildcardFileExtension);

            for (Path fileEntry : filesToLoad) {
                List<T> loadedEntries = loadFile(fileEntry);
                for (T entry : loadedEntries) {
                    result.put(entry.getId(), entry);
                }
            }
        }
        return result;
    }

    @Override
    public POJOLoader<T> setWildcardFileExtension(String... wildcards) {
        wildcardFileExtension = SetUtils.buildSet(wildcards);
        return this;
    }
}
