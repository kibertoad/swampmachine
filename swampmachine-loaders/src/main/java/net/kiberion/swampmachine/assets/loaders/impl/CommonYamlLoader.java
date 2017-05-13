package net.kiberion.swampmachine.assets.loaders.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.kiberion.swampmachine.utils.SetUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.kiberion.swampmachine.assets.loaders.api.EntityYamlLoader;
import net.kiberion.swampmachine.assets.loaders.util.FileReaderFactory;
import net.kiberion.swampmachine.assets.loaders.util.YamlLoader;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.entities.common.impl.AbstractModelEntityDescriptor;

public abstract class CommonYamlLoader<T extends AbstractModelEntityDescriptor> implements EntityYamlLoader<T> {

    private static final Logger log = LogManager.getLogger();

    private Path path;
    private AbstractFileReader fileReader;
    private YamlLoader yamlLoader = new YamlLoader();

    // when doing "*" file mass-loading, only parse files with this extension
    public Set<String> wildcardFileExtension = SetUtils.buildSet();

    protected Map<String, T> results = new HashMap<>();

    // Block of YAML attributes that are parsed
    // Note that Swampmachine expects certain attribute names that are not
    // modifiable - convention over configuration.
    private static final String NAME_ATTRIBUTE = "name";
    private static final String ID_ATTRIBUTE = "id";
    private static final String DESCRIPTION_ATTRIBUTE = "desc";

    public CommonYamlLoader(Path fromPath) {
        path = fromPath;
        this.fileReader = FileReaderFactory.buildFileReader(path);
    }

    public CommonYamlLoader(String fromPath) {
        this(Paths.get(fromPath.replace("*", "")));
    }

    /**
     * Parse YAML object for generic data. Call it in the beginning of the
     * iteration loop
     */
    protected void parseYaml(Object sourceYamlObject, T targetObject) {
        yamlLoader.setNextYamlNode(sourceYamlObject);

        targetObject.setName(yamlLoader.getString(NAME_ATTRIBUTE));
        targetObject.setId(yamlLoader.getString(ID_ATTRIBUTE));

        targetObject.setDescription(
                yamlLoader.hasKey(DESCRIPTION_ATTRIBUTE) ? yamlLoader.getString(DESCRIPTION_ATTRIBUTE) : "");
    }

    public void loadAllEntities(Path name) throws IOException {
        try (InputStream is = fileReader.getFileAsStream(name)) {
            Iterable<Object> dataYamls = yamlLoader.loadYamlObjects(is);
            for (Object o : dataYamls) {
                parseYaml(o, initNewEntity());
            }
        }
    }

    protected abstract T initNewEntity();

    @Override
    public Map<String, T> loadMap() {
        try {
            // Parse one file
            if (isLoadSingle()) {
                loadAllEntities(path);
            }

            // Parse whole directory
            else {
                List<Path> filesToLoad = null;
                try {
                    filesToLoad = fileReader.getListOfFilesByWildcard(path, wildcardFileExtension);
                } catch (IOException e) {
                    log.error("IO exception: ", e);
                    throw new IllegalStateException("Failed to get list of files: ", e);
                }

                for (Path entry : filesToLoad) {
                    loadAllEntities(entry);
                }
            }
        }

        catch (IOException e) {
            log.error("IO exception", e);
            throw new IllegalStateException(e);
        }

        return results;

    }

    @Override
    public CommonYamlLoader<T> setSupportedFileExtensions(String... wildcards) {
        wildcardFileExtension = SetUtils.buildSet(wildcards);
        return this;
    }

    @Override
    public void setFileReader(AbstractFileReader fileReader) {
        this.fileReader = fileReader;
    }

    protected YamlLoader getYamlLoader() {
        return yamlLoader;
    }

    private boolean isLoadSingle() {
        return (wildcardFileExtension.isEmpty());
    }

}
