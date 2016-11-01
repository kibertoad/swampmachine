package net.kiberion.swampmachine.blueprints.common.loaders;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.blueprints.common.entities.api.Trait;
import net.kiberion.swampmachine.blueprints.common.registries.TraitRegistry;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.loaders.CommonSyncStartupLoader;

public class TraitLoader<T extends Trait & EntityModelDescriptor> extends CommonSyncStartupLoader {

    @Autowired
    private TraitRegistry<T> registry;

    private final Class<T> traitClass;

    // convention over configuration in this case - if you are using
    // Swampmachine asset management, you are expected to follow path convention
    private static final String TRAITS_MODEL_DIRECTORY = "model-traits";
    private static final String TRAITS_ENTITIES_FILE_EXTENSION = "traits";

    public TraitLoader(Class<T> traitClass) {
        this.traitClass = traitClass;
    }

    @Override
    public int getPriority() {
        return 51;
    }

    @Override
    public Class<T> getEntityClass() {
        return traitClass;
    }

    @Override
    public String getLoadDirectory() {
        return TRAITS_MODEL_DIRECTORY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, T> getTargetMap() {
        return registry.getTraits();
    }

    @Override
    public String getLoadFileExtension() {
        return TRAITS_ENTITIES_FILE_EXTENSION;
    }

}
