package net.kiberion.swampmachine.registries;

import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.ImmutableSet;

import net.kiberion.swampmachine.loaders.ResourcesLoader;

/**
 * This class should only be used for YAML deserialization.
 * 
 *  Gets filled with data from {@link ResourcesLoader} after loading is done.
 * 
 * @author kibertoad
 *
 */
public class StaticModelInfoRegistry {

    private StaticModelInfoRegistry () {}
    
    private static Set<String> existingResources;

    public static Set<String> getExistingResources() {
        Validate.notNull(existingResources, "Existing resources should be set at this point, but were null.");
        return existingResources;
    }
    
    public static void setExistingResources(Set<String> existingResources) {
        if (StaticModelInfoRegistry.existingResources != null) {
            throw new IllegalStateException ("Existing resources can be only set once.");
        }
        StaticModelInfoRegistry.existingResources = ImmutableSet.copyOf(existingResources);
    }
        
}
