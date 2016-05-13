
package net.kiberion.swampmachine.entities.modelinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;


/**
 * @author kibertoad
 */
public class ActionInfo extends CommonModelEntityDescriptor {

    public enum TargetType {
        SELF,
        SINGLE_OPPONENT, ALL_OPPONENT, DECK_SINGLE_OPPONENT,
        SINGLE_ALLIED, ALL_ALLIED, DECK_SINGLE_ALLIED};

    public TargetType targetType;

    public List<ActionEffect> actionEffects = new ArrayList<>();
    public Map<String, Integer> resourcesUsed = new HashMap<>();

    public int cooldown;
    public int time;
}
