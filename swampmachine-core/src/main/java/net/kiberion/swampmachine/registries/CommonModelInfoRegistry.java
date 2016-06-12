package net.kiberion.swampmachine.registries;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourceDescriptor;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesDelta;
import net.kiberion.swampmachine.entities.modelinfo.ActionDescriptor;
import net.kiberion.swampmachine.entities.modelinfo.GroupDescriptor;

@ImmutableRegistry
public class CommonModelInfoRegistry{

	@Getter
    private final Map<String, ResourceDescriptor> resources = new HashMap<>();
    
	@Getter
	private Set<String> existingResources;

    public void setExistingResources(Set<String> existingResources) {
        this.existingResources = ImmutableSet.copyOf(existingResources);
    }
	
	public ResourcesDelta getNewResourcesDeltaInstance () {
	    return new ResourcesDelta(existingResources);
	}
	
	@Getter
    private Map<String, GroupDescriptor> actionGroups;
	
	@Getter
    private Map<String, ActionDescriptor> actions;
	
}
