package net.kiberion.swampmachine.registries;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;

/**
 * @author kibertoad
 *
 */
@ImmutableRegistry
public class ListenerClassRegistry extends AbstractClassRegistry<ChangeListener>{

    @Getter
    private Map<String, Class<? extends ChangeListener>> listeners = new HashMap<>();

    public ListenerClassRegistry(Collection<String> packagesToScan) {
        scanPackages (packagesToScan, ChangeListener.class);
    }

    @Override
    protected Map<String, Class<? extends ChangeListener>> getEntities() {
        return listeners;
    }

}
