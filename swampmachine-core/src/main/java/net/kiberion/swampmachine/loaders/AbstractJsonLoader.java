package net.kiberion.swampmachine.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.utils.SetUtils;

public abstract class AbstractJsonLoader<E extends EntityModelDescriptor> extends AbstractLoader {

    protected List<E> loadInternal(Resource source) {
        Validate.notNull(source);
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = source.getInputStream()) {
            return mapper.readValue(is, getTypeReference());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }    

    protected abstract TypeReference<List<E>> getTypeReference ();

    @Override
    public abstract Class<E> getEntityClass();

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends EntityModelDescriptor> void loadDataNodes(Map<String, T> targetMap, String loadDirectory,
            String loadExtension, Class<T> clazz) {

        try {
            if (fileExists(loadDirectory)) {
                Path entityDirectory = getEntityDirectory(loadDirectory);

                AbstractFileReader reader = getReaderHelper().getReader(entityDirectory);

                getLog().info("Loading entities from: " + entityDirectory.toString());

                List<E> entities = new ArrayList<>();

                List<Path> filesToLoad = reader.getListOfFilesByWildcard(entityDirectory,
                        SetUtils.buildSet(loadExtension));
                for (Path entry : filesToLoad) {
                    getLog().info("Currently loading: "+entry);
                    entities.addAll(loadInternal(new PathResource(entry)));
                }
                for (E entity : entities) {
                    targetMap.put(entity.getId(), (T) entity);
                }

                getLog().info("Loaded " + entities.size() + " " + getEntityClass().getSimpleName() + " entities.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while loading: ", e);
        }
    }

    @Override
    public String getLoadFileExtension() {
        return "json";
    }
}
