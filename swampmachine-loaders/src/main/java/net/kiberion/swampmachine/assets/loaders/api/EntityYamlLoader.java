package net.kiberion.swampmachine.assets.loaders.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;


/**
 * @author kibertoad
 */
public interface EntityYamlLoader<T extends EntityModelDescriptor> {

    /**
     * 
     * @return Map of loaded entities, where key is id of an entity, and value is an entity
     * @throws IOException
     */
    public Map<String, T> loadMap() throws IOException;

    public EntityYamlLoader<T> setSupportedFileExtensions(String... extensions);

    public void setFileReader(AbstractFileReader fileReader);

    public default List<T> loadList() throws IOException {
        return new ArrayList<>(loadMap().values());
    }
}
