package net.kiberion.entities.modelinfo;

import net.kiberion.entities.common.impl.DataNode;
import net.kiberion.groovy.GroovyPyramideScript;



/**
 * @author: kibertoad
 */
public class ItemInfo extends DataNode {

	/*
    public ValueSet<DataNode> values = new ValueSet<DataNode>(); //Item stats


    public ValueSet<DataNode> craftingIngredients = new ValueSet<DataNode>();

    public Int maxHp = new Int (0);
    public Int price;

    //public int itemType; //to what itemGroup does it belong

    public List<ActionInfo> effectsOnHit = new ArrayList<ActionInfo>();
    public List<ActionInfo> possibleActions = new ArrayList<ActionInfo>(); //e. g. you can swing and thrust with sword, chop trees with axe

    public ActionsBonusSet actionsBonusSet;

    public ItemGroupInfo itemGroupInfo;
    public GroupInfo itemSubGroupInfo = null;

    public CombatEffectInfo combatInfo = null;

*/

    public GroovyPyramideScript invokeEffectScript; //effect on being invoked, e. g. eaten
    public GroovyPyramideScript pickupEffectScript = null;


    public boolean consumedOnUse = false;
    public boolean stackable = true;

    /*
    public ActionInfo getDefaultAction () {
        if ((itemSubGroupInfo == null) || (itemSubGroupInfo.defaultAction == null)) {
            return itemGroupInfo.defaultAction;
        } else {
            return itemSubGroupInfo.defaultAction;
        }
    }

    public void setHp (int hp) {
        maxHp.setIntValue( hp);
    }

    public void setActions (List<String> actionIDs) {
        for (String s: actionIDs) {
            possibleActions.add(AssetProvider.instanceNoInit().getActions().getByCode(s));
        }
    }
    */

}
