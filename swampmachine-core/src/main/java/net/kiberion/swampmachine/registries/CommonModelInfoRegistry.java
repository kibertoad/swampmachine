package net.kiberion.swampmachine.registries;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.entities.modelinfo.ActionInfo;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;
import net.kiberion.swampmachine.entities.modelinfo.GroupInfo;
import net.kiberion.swampmachine.entities.modelinfo.ItemInfo;
import net.kiberion.swampmachine.entities.modelinfo.PersonalName;

@Component
public class CommonModelInfoRegistry{

	@Getter
    private Map<String, CreatureModelInfo> creatures = new HashMap<>();
    
    private Map<String, PersonalName> creatureNames;
    //private Map<String, StatInfo> creatureStats;
    //private Map<String, StatInfoTemplate> creatureTemplateStats;
    
    //private Map<String, BuildingModelInfo> buildings;
    
    //private Map<String, GroupInfo> bodypartGroups;
    //private Map<String, BodyPartInfo> bodyparts;

    //private Map<String, StatInfo> itemStats;
    //private Map<String, ItemGroupInfo> itemGroups;
    private Map<String, ItemInfo> items;
    //private Map<String, ListOfNodes> itemSets;

    private Map<String, CommonModelEntityDescriptor> resources;
    
    //private Map<String, EquipmentSlotInfo> equipmentSlots;    
    
    //private Map<String, FormulaInfo> formulas;
    
    //private Map<String, GroupInfo> perkGroups;
    //private Map<String, PerkInfo> perks;
    //private Map<String, SkillInfo> skills;

    private Map<String, GroupInfo> actionGroups;
    private Map<String, ActionInfo> actions;

    //private Map<String, GroupInfo> locationGroups;
    //private Map<String, LocationInfo> locations;	
	
}
