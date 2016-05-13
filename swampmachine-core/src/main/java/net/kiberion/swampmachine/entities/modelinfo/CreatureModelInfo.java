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
public class CreatureModelInfo extends CommonModelEntityDescriptor{

    public int hp;
    public int sightRange;
    public int speed;

    public List<ActionInfo> actions;
    public Map<String, Integer> statList;
    public Map<String, Integer> skillList;

    //public Inventory inventory;
    //public Inventory equippedItems;
    public String templateID = null;

    public CreatureModelInfo() {
    }
    
    public CreatureModelInfo(String setName, String setCode, Map<String, ItemInfo> items) {
        super(setName, setCode);

        actions = new ArrayList<>();
        //inventory = new Inventory(items);
        //equippedItems = new Inventory(items);
    }

    public Map<String, Integer> getStatList() {
        return statList;
    }

    public Map<String, Integer> getSkillList() {
        return statList;
    }

    /*
    public Inventory getInventory() {
        return inventory;  //To change body of created methods use File | Settings | File Templates.
    }
    */
}
