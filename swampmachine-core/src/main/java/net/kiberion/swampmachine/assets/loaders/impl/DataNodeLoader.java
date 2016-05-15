/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.assets.loaders.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;

import net.kiberion.swampmachine.assets.loaders.api.AbstractLoader;
import net.kiberion.swampmachine.assets.loaders.util.GroovyTranslator;
import net.kiberion.swampmachine.assets.loaders.util.YamlLoader;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.assets.readers.GDXFileReader;
import net.kiberion.swampmachine.assets.readers.SimpleFileReader;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.entities.modelinfo.GroupInfo;
import net.kiberion.swampmachine.groovy.GroovyScript;
import net.kiberion.swampmachine.utils.SetUtils;

/**
 * This loader is not thread-safe.
 * Note that it exists mostly as a reference for few useful bits is still contains, this class is poorly designed and too monolothic to
 * be considered a worthy part of the library
 * 
 * @author kibertoad
 */

@Deprecated
public class DataNodeLoader<T extends CommonModelEntityDescriptor> implements AbstractLoader<T> {

    private static final Logger log = LogManager.getLogger();
    protected GroovyTranslator translator;

    protected List<String> globalTags = new ArrayList<>();
    protected List<GroovyScript> globalReqs = new ArrayList<>();
    protected List<GroovyScript> globalEffects = new ArrayList<>();
    protected String globalGroup = null;
    protected String globalSubGroup = null;

    protected String entityName;
    protected String entityCode;
    protected String entityDescription;
    protected int entityRating;
    protected Path path;

    public Set<String> wildcardFileExtension = SetUtils.buildSet(); // when
                                                                    // doing "*"
                                                                    // file
                                                                    // mass-loading,
                                                                    // only
                                                                    // parse
                                                                    // files
                                                                    // with this
                                                                    // extension

    protected Map<String, T> results = new HashMap<>();
    protected YamlLoader yamlLoader = new YamlLoader();

    private AbstractFileReader fileReader;

    public DataNodeLoader() {
        this((Path) null);
    }

    public DataNodeLoader(Path fromPath) {
        path = fromPath;
        translator = new GroovyTranslator();

        if (Gdx.app != null) {
            this.fileReader = new GDXFileReader(fromPath);
        } else {
            this.fileReader = new SimpleFileReader(fromPath);
        }
    }

    public DataNodeLoader(String fromPath) {
        this(Paths.get(fromPath.replace("*", "")));
    }

    protected void setTags(CommonModelEntityDescriptor node) {
        if (yamlLoader.hasKey("tags")) {
            yamlLoader.getList("tags");

            for (Object o : yamlLoader.list) {
                node.addTag((String) o);
                // Gdx.app.log("debug", "added tag: "+(String)o);
            }
        }

        for (Object o : globalTags) {
            node.addTag((String) o);
        }
    }

    protected void fillGlobalReqs() {
        if (yamlLoader.hasKey("globalreqs")) {
            yamlLoader.getList("globalreqs");

            for (Object o : yamlLoader.list) {
                // globalReqs.add(translator.convertString((String) o));
                globalReqs.add(new GroovyScript((String) o));
            }
        }
    }

    protected void fillGlobalEffects() {
        if (yamlLoader.hasKey("globaleffects")) {
            yamlLoader.getList("globaleffects");

            for (Object o : yamlLoader.list) {
                globalEffects.add(new GroovyScript((String) o));
            }
        }
    }

    protected void fillGlobalTags() {
        if (yamlLoader.hasKey("globaltags")) {
            yamlLoader.getList("globaltags");

            globalTags.clear();

            for (Object o : yamlLoader.list) {
                globalTags.add((String) o);
            }
        }
    }

    protected void parseDefinitions(Map<String, T> result) {
        if (yamlLoader.hasKey("define")) {
            yamlLoader.getList("define");

            for (Object o : yamlLoader.list) {
                String s1 = ((String) o).split(" ")[0];
                String s2 = ((String) o).split(" ")[1];

                // ToDo implement cleanly
                /*
                 * if (s1.equals("integer")) { result.addCustomValueInt(s2);
                 * Gdx.app.log("debug", "Defined new custom value: " + s2); }
                 * 
                 * if (s1.equals("string")) { result.addCustomValueString(s2); }
                 */
            }
        }
    }

    protected void parseCustomFields(Map<String, T> result, CommonModelEntityDescriptor node) {

        // ToDo implement cleanly
        /*
         * for (String s : result.customValuesInt) { if (ya.hasKey(s)) {
         * node.setCustomValue(s, ya.getInteger(s)); // Gdx.app.log("debug",
         * "Added new custom value: " + s); } }
         * 
         * for (String s : result.customValuesString) { if (ya.hasKey(s)) {
         * node.setCustomValue(s, ya.getString(s)); } }
         */
    }

    private String getStringForKey(String key) {
        if (yamlLoader.hasKey(key)) {
            return yamlLoader.getString(key);
        } else {
            return null;
        }
    }

    protected void setGroup(CommonModelEntityDescriptor node, Map<String, GroupInfo> groups) {
        globalGroup = getStringForKey("globalgroup");
        globalSubGroup = getStringForKey("globalsubgroup");

        if (yamlLoader.hasKey("group")) {
            node.setGroup(yamlLoader.getString("group"));

            if (groups.get(node.getGroup()) == null) {
                log.error("Unknown group: " + node.getGroup());
            }

            node.setGroupID(groups.get(node.getGroup()).getId());
        } else {
            if (globalGroup != null) {
                node.setGroup(globalGroup);
                node.setGroupID(groups.get(node.getGroup()).getId());
            }
        }

        if (yamlLoader.hasKey("subgroup")) {
            node.setSubGroup(yamlLoader.getString("subgroup"));

            // if (groups.getByCode(node.subGroupCode) == null) {
            // Log.error("Unknown subgroup: " + node.subGroupCode);
            // }

            Objects.requireNonNull(groups);
            Objects.requireNonNull(groups.get(node.getGroup()));
            Objects.requireNonNull(groups.get(node.getGroup()).subGroups);
            Objects.requireNonNull(groups.get(node.getGroup()).subGroups.get(node.getSubGroup()));

            node.setSubGroupID(groups.get(node.getGroup()).subGroups.get(node.getSubGroup()).getId());
        } else {
            if (globalSubGroup != null) {
                node.setSubGroup(globalSubGroup);
                node.setSubGroupID(groups.get(node.getGroup()).subGroups.get(node.getSubGroup()).getId());
            }
        }
    }

    /**
     * Parse YAML object for generic data. Call it in the beginning of the
     * iteration loop
     */
    protected void parseYaml(Object o) {
        yamlLoader.setNextYamlNode(o);

        entityName = yamlLoader.getString("name");
        entityCode = yamlLoader.getString("id");

        if (yamlLoader.hasKey("rating")) {
            entityRating = yamlLoader.getInteger("rating");
        } else {
            entityRating = -1;
        }

        if (yamlLoader.hasKey("desc")) {
            entityDescription = yamlLoader.getString("desc");
        } else {
            entityDescription = "";
        }
    }

    public void loadAllEntities() {
        for (Object o : yamlLoader.dataYamls) {
            parseYaml(o);
        }
    }

    /**
     * Method used when loader is not used as a superclass for more advanced
     * loader
     * 
     * @return
     */
    public Map<String, T> loadAndInitialize() {
        if (wildcardFileExtension.isEmpty())
        // Parse one file
        {
            yamlLoader.openFile(path);
            loadAndInitializeAllEntities();
        }

        return results;
    }

    private void loadAndInitializeAllEntities() {
        for (Object o : yamlLoader.dataYamls) {
            parseYaml(o);

            CommonModelEntityDescriptor node = new CommonModelEntityDescriptor();
            node.setName(entityName);
            node.setDescription(entityDescription);
            node.setId(entityCode);
            results.put(node.getId(), (T) node);
        }
    }

    public boolean loadSingle() {
        return (wildcardFileExtension.isEmpty());
    }

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
                e.printStackTrace();
            }

            for (Path entry : filesToLoad) {
                yamlLoader.openFile(entry);
                loadAllEntities();
            }
            // }
        }

        return results;
    }

    @Override
    public DataNodeLoader<T> setWildcardFileExtension(String... wildcards) {
        wildcardFileExtension = SetUtils.buildSet(wildcards);
        return this;
    }

    @Override
    public void setFileReader(AbstractFileReader fileReader) {
        this.fileReader = fileReader;
    }
}
