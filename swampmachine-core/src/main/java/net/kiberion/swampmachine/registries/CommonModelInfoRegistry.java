package net.kiberion.swampmachine.registries;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.entities.modelinfo.ActionInfo;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;
import net.kiberion.swampmachine.entities.modelinfo.GroupInfo;
import net.kiberion.swampmachine.entities.modelinfo.ItemInfo;

public class CommonModelInfoRegistry{

	@Getter
    private Map<String, CreatureModelInfo> creatures = new HashMap<>();

	@Getter
    private Map<String, ItemInfo> items;

	@Getter
    private Map<String, CommonModelEntityDescriptor> resources;
    
	@Getter
    private Map<String, GroupInfo> actionGroups;
	
	@Getter
    private Map<String, ActionInfo> actions;

	
}
