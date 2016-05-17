package net.kiberion.swampmachine.entities.modelinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 * User: kibertoad
 *
 * This class is used to describe template information for creature
 */

//ToDo Too much specific logic to belong in core library. Make it more generic and put specific implementation into Blueprints
@Deprecated
public class CreatureModelInfo extends CommonModelEntityDescriptor{

    public int hp;
    public int sightRange;
    public int speed;

    public List<ActionInfo> actions;
    public Map<String, Integer> statList;
    public Map<String, Integer> skillList;

    public String templateID = null;

    public CreatureModelInfo() {
    }
    
    public CreatureModelInfo(String setName, String setCode, Map<String, ItemInfo> items) {
        super(setName, setCode);

        actions = new ArrayList<>();
    }

    public Map<String, Integer> getStatList() {
        return statList;
    }

    public Map<String, Integer> getSkillList() {
        return statList;
    }

}
