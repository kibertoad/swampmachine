package net.kiberion.swampmachine.registries;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourceDescriptor;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesDelta;
import net.kiberion.swampmachine.entityblocks.api.EntityMetadataProvider;

/**
 * Registry for game resources.
 * @author kibertoad
 *
 */

@ImmutableRegistry
public class ResourceRegistry implements EntityMetadataProvider<ResourceDescriptor> {

    @Getter
    private final Map<String, ResourceDescriptor> resources = new HashMap<>();
    
    @Getter
    private Set<String> existingResources;

    //this injection would create a circular dependency, so is a no-no
    //@Value("#{resourcesLoader.loadAndGetResourceIDs()}")
    public void setExistingResources(Collection<String> existingResources) {
        this.existingResources = ImmutableSet.copyOf(existingResources);
    }
    
    public ResourcesDelta getNewResourcesDeltaInstance () {
        return new ResourcesDelta(existingResources);
    }

    @Override
    public ResourceDescriptor getMetadataForEntity(String entityId) {
        return resources.get(entityId);
    }

}
