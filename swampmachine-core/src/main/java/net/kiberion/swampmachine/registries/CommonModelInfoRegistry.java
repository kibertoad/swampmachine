package net.kiberion.swampmachine.registries;

import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.entities.modelinfo.ActionDescriptor;
import net.kiberion.swampmachine.entities.modelinfo.GroupDescriptor;

@ImmutableRegistry
public class CommonModelInfoRegistry{

	@Getter
    private Map<String, GroupDescriptor> actionGroups;
	
	@Getter
    private Map<String, ActionDescriptor> actions;
	
}
