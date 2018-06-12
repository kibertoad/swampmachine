package net.kiberion.swampmachine.registries;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;

/**
 * Stores "filename (without extension) -> view info" map
 * @author kibertoad
 *
 */
@ImmutableRegistry
public class ImageRegistry {

    @Getter
    private Map<String, ViewInfo> images = new HashMap<>();
    
}
