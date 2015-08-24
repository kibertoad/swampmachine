/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.assets.loaders;

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

import net.kiberion.assets.readers.AbstractFileReader;
import net.kiberion.assets.readers.GDXFileReader;
import net.kiberion.assets.readers.SimpleFileReader;
import net.kiberion.entities.common.impl.DataNode;
import net.kiberion.entities.modelinfo.GroupInfo;
import net.kiberion.groovy.GroovyPyramideScript;
import net.kiberion.utils.SetUtils;

/**
 * @author kibertoad
 */
public class DataNodeLoader<t extends DataNode> implements AbstractLoader<t> {

	private static final Logger log = LogManager.getLogger();
    protected GroovyTranslator translator;

    protected List<String> globalTags = new ArrayList<>();
    protected List<GroovyPyramideScript> globalReqs = new ArrayList<>();
    protected List<GroovyPyramideScript> globalEffects = new ArrayList<>();
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

    protected Map<String, t> results = new HashMap<String, t>();
    protected YamlLoader ya = new YamlLoader();

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

    protected void setTags(DataNode node) {
        if (ya.hasKey("tags")) {
            ya.getList("tags");

            for (Object o : ya.list) {
                node.addTag((String) o);
                // Gdx.app.log("debug", "added tag: "+(String)o);
            }
        }

        for (Object o : globalTags) {
            node.addTag((String) o);
        }
    }

    protected void fillGlobalReqs() {
        if (ya.hasKey("globalreqs")) {
            ya.getList("globalreqs");

            for (Object o : ya.list) {
                // globalReqs.add(translator.convertString((String) o));
                globalReqs.add(new GroovyPyramideScript((String) o));
            }
        }
    }

    protected void fillGlobalEffects() {
        if (ya.hasKey("globaleffects")) {
            ya.getList("globaleffects");

            for (Object o : ya.list) {
                globalEffects.add(new GroovyPyramideScript((String) o));
            }
        }
    }

    protected void fillGlobalTags() {
        if (ya.hasKey("globaltags")) {
            ya.getList("globaltags");

            globalTags.clear();

            for (Object o : ya.list) {
                globalTags.add((String) o);
            }
        }
    }

    protected void parseDefinitions(Map<String, t> result) {
        if (ya.hasKey("define")) {
            ya.getList("define");

            for (Object o : ya.list) {
                String s1 = ((String) o).split(" ")[0];
                String s2 = ((String) o).split(" ")[1];

                //ToDo implement cleanly
                /*
                if (s1.equals("integer")) {
                    result.addCustomValueInt(s2);
                    Gdx.app.log("debug", "Defined new custom value: " + s2);
                }

                if (s1.equals("string")) {
                    result.addCustomValueString(s2);
                }
                */
            }
        }
    }

    protected void parseCustomFields(Map<String, t> result, DataNode node) {

    	//ToDo implement cleanly
    	/*
        for (String s : result.customValuesInt) {
            if (ya.hasKey(s)) {
                node.setCustomValue(s, ya.getInteger(s));
                // Gdx.app.log("debug", "Added new custom value: " + s);
            }
        }

        for (String s : result.customValuesString) {
            if (ya.hasKey(s)) {
                node.setCustomValue(s, ya.getString(s));
            }
        }
        */
    }

    protected void setGroup(DataNode node, Map<String, GroupInfo> groups) {

        if (ya.hasKey("globalgroup")) {
            globalGroup = ya.getString("globalgroup");
        }

        if (ya.hasKey("globalsubgroup")) {
            globalSubGroup = ya.getString("globalsubgroup");
        }

        if (ya.hasKey("group")) {
            node.group = ya.getString("group");

            if (groups.get(node.group) == null) {
                log.error("Unknown group: " + node.group);
            }

            node.groupID = groups.get(node.group).getId();
        } else {
            if (globalGroup != null) {
                node.group = globalGroup;
                node.groupID = groups.get(node.group).getId();
            }
        }

        if (ya.hasKey("subgroup")) {
            node.subGroup = ya.getString("subgroup");

            // if (groups.getByCode(node.subGroupCode) == null) {
            // Log.error("Unknown subgroup: " + node.subGroupCode);
            // }

            Objects.requireNonNull(groups);
            Objects.requireNonNull(groups.get(node.group));
            Objects.requireNonNull(groups.get(node.group).subGroups);
            Objects.requireNonNull(groups.get(node.group).subGroups.get(node.subGroup));

            node.subGroupID = groups.get(node.group).subGroups.get(node.subGroup).getId();
        } else {
            if (globalSubGroup != null) {
                node.subGroup = globalSubGroup;
                node.subGroupID = groups.get(node.group).subGroups.get(node.subGroup).getId();
            }
        }
    }

    /**
     * Parse YAML object for generic data. Call it in the beginning of the
     * iteration loop
     */
    protected void parseYaml(Object o) {
        ya.setNextYamlNode(o);

        entityName = ya.getString("name");
        entityCode = ya.getString("id");

        if (ya.hasKey("rating")) {
            entityRating = ya.getInteger("rating");
        } else {
            entityRating = -1;
        }

        if (ya.hasKey("desc")) {
            entityDescription = ya.getString("desc");
        } else {
            entityDescription = "";
        }
    }

    public void loadAllEntities() {
        for (Object o : ya.dataYamls) {
            parseYaml(o);
        }
    }

    /**
     * Method used when loader is not used as a superclass for more advanced
     * loader
     * 
     * @return
     */
    public Map<String, t> loadAndInitialize() {
        if (wildcardFileExtension.isEmpty())
        // Parse one file
        {
            ya.openFile(path);
            loadAndInitializeAllEntities();
        }

        return results;
    }

    private void loadAndInitializeAllEntities() {
        for (Object o : ya.dataYamls) {
            parseYaml(o);

            DataNode node = new DataNode();
            node.setName(entityName);
            node.setDescription (entityDescription);
            node.setId(entityCode);
            results.put(node.getId(), (t) node);
        }
    }

    public boolean loadSingle() {
        return (wildcardFileExtension.isEmpty());
    }

    @Override
    public Map<String, t> load() {

        if (loadSingle())
        // Parse one file
        {
            ya.openFile(path);
            loadAllEntities();
        } else

        // Parse whole directory
        {
            // path = path.replace("*", "");

            // FileHandle dirHandle = Gdx.files.internal(path);
            // ArrayList<FileHandle> list = new ArrayList<FileHandle>();

            /*
             * for (FileHandle entry : dirHandle.list()) {
             * 
             * //Load common file first if
             * (entry.path().contains("common."+wildcardFileExtension.get(0))) {
             * ya.openFile(entry); loadAllEntities(); } else {
             * 
             * if (wildcardFileExtension.contains(entry.extension())) {
             * list.add(entry); } } }
             */

            // for (FileHandle entry : list) {
            // ya.openFile(entry);
            // loadAllEntities();
            // }

            List<Path> filesToLoad = null;
            try {
                filesToLoad = fileReader.getListOfFilesByWildcard(path, wildcardFileExtension);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Path entry : filesToLoad) {
                ya.openFile(entry);
                loadAllEntities();
            }
            // }
        }

        return results;
    }

    @Override
    public DataNodeLoader<t> setWildcardFileExtension(String... wildcards) {
        wildcardFileExtension = SetUtils.buildSet(wildcards);
        return this;
    }

    @Override
    public void setFileReader(AbstractFileReader fileReader) {
        // TODO Auto-generated method stub

    }
}
