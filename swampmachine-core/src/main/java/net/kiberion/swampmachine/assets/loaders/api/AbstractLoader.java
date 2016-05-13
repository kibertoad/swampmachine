package net.kiberion.swampmachine.assets.loaders.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.entities.common.impl.EntityModel;


/**
 * @author kibertoad
 */
public interface AbstractLoader<T extends EntityModel> {

    public Map<String, T> load() throws IOException;

    public AbstractLoader<T> setWildcardFileExtension(String... wildcards);

    public void setFileReader(AbstractFileReader fileReader);

    public default List<T> loadList() throws IOException {
        return new ArrayList<T>(load().values());
    }
}
