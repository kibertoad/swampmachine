package net.kiberion.entities.modelinfo;

import java.util.HashMap;

import net.kiberion.utils.Dice;

/**
 * @author: kibertoad
 */
public class CreatureInfoList extends HashMap<String, CreatureModelInfo> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8518857954278432484L;
	int indexValue;

    public CreatureModelInfo getRandom() {
        indexValue = Dice.getRandomInt(0, this.size()).getIntValue();
        return this.get(indexValue);
    }
}
