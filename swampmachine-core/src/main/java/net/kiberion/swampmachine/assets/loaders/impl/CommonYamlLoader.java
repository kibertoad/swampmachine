package net.kiberion.swampmachine.assets.loaders.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;

import net.kiberion.swampmachine.assets.loaders.api.AbstractLoader;
import net.kiberion.swampmachine.assets.loaders.util.YamlLoader;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.assets.readers.GDXFileReader;
import net.kiberion.swampmachine.assets.readers.SimpleFileReader;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.utils.SetUtils;

public abstract class CommonYamlLoader<T extends CommonModelEntityDescriptor> implements AbstractLoader<T> {

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

        if (Gdx.app != null) {
            this.fileReader = new GDXFileReader(fromPath);
        } else {
            this.fileReader = new SimpleFileReader(fromPath);
        }
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

        targetObject.getMetadata().setName(yamlLoader.getString(NAME_ATTRIBUTE));
        targetObject.getMetadata().setId(yamlLoader.getString(ID_ATTRIBUTE));

        targetObject.getMetadata().setDescription(
                yamlLoader.hasKey(DESCRIPTION_ATTRIBUTE) ? yamlLoader.getString(DESCRIPTION_ATTRIBUTE) : "");
    }

    public void loadAllEntities() {
        for (Object o : yamlLoader.dataYamls) {
            parseYaml(o, initNewEntity());
        }
    }

    protected abstract T initNewEntity();

    @Override
    public Map<String, T> load() {
        if (loadSingle())
        // Parse one file
        {
            yamlLoader.openFile(path);
            loadAllEntities();
        } else
        // Parse whole directory
        {
            List<Path> filesToLoad = null;
            try {
                filesToLoad = fileReader.getListOfFilesByWildcard(path, wildcardFileExtension);
            } catch (IOException e) {
                log.error("IO exception: ", e);
                throw new IllegalStateException ("Failed to get list of files: ", e);
            }

            for (Path entry : filesToLoad) {
                yamlLoader.openFile(entry);
                loadAllEntities();
            }
        }

        return results;
    }

    @Override
    public CommonYamlLoader<T> setWildcardFileExtension(String... wildcards) {
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

    private boolean loadSingle() {
        return (wildcardFileExtension.isEmpty());
    }



}
