package net.kiberion.swampmachine.gui.composer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;

import net.kiberion.swampmachine.assets.util.LoadBeforeStartup;
import net.kiberion.swampmachine.loaders.AbstractJsonLoader;

@LoadBeforeStartup
public class CompositionLoader extends AbstractJsonLoader<Composition> {

    private static final String COMPOSITION_DIRECTORY = "view-compositions";

    @Autowired
    private CompositionRegistry compositionRegistry;

    public CompositionLoader() {
    }

    public CompositionLoader(int priority) {
        this.setPriority(priority);
    }

    @Override
    protected TypeReference<List<Composition>> getTypeReference() {
        return new TypeReference<List<Composition>>(){};
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
    public Class<Composition> getEntityClass() {
        return Composition.class;
    }

}
