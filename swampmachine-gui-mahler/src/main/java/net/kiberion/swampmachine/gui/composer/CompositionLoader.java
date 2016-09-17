package net.kiberion.swampmachine.gui.composer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.kiberion.swampmachine.assets.loaders.util.FileReaderFactory;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.loaders.AbstractLoader;
import net.kiberion.swampmachine.utils.MapUtils;
import net.kiberion.swampmachine.utils.SetUtils;

public class CompositionLoader extends AbstractLoader{

    private static final Logger log = LogManager.getLogger();
    
    private static final String COMPOSITION_DIRECTORY = "view-compositions";
    
    @Autowired
    private CompositionRegistry compositionRegistry;
    
    private AbstractFileReader fileReader;
    
    
    public List<Composition> loadInternal (Resource source) {
        Validate.notNull(source);
        ObjectMapper mapper = new ObjectMapper();
        
        try (InputStream is = source.getInputStream()){
            return mapper.readValue(is, CompositionTree.class).getCompositions();
        } catch (IOException e) {
            throw new IllegalStateException (e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends EntityModelDescriptor> void loadDataNodes(Map<String, T> targetMap, String loadDirectory,
            String loadExtension, Class<T> clazz) {
        
        
        try {
            if (fileExists(loadDirectory)) {
                Path entityDirectory = getEntityDirectory(loadDirectory);
                this.fileReader = FileReaderFactory.buildFileReader(entityDirectory);

                log.info("Loading entities from: " + entityDirectory.toString());

                List<Composition> entities = new ArrayList<>();
                
                List<Path> filesToLoad = fileReader.getListOfFilesByWildcard(entityDirectory, SetUtils.buildSet(loadExtension));
                for (Path entry : filesToLoad) {
                    entities.addAll(loadInternal(new PathResource (entry)));
                }                
                
                MapUtils.putAllEntities((Map<String, Composition>)targetMap, entities);
                log.info("Loaded " + entities.size() + " " + getEntityClass().getSimpleName() + " entities.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Composition> getTargetMap() {
        return compositionRegistry.getCompositions();
    }

    @Override
    public String getLoadDirectory() {
        return COMPOSITION_DIRECTORY;
    }

    @Override
    public String getLoadFileExtension() {
        return "json";
    }

    @Override
    public Class<? extends EntityModelDescriptor> getEntityClass() {
        return Composition.class;
    }
    
}
