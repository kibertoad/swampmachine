
package net.kiberion.swampmachine.entities.modelinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 * @author kibertoad
 */
public class ActionInfo extends CommonModelEntityDescriptor {

    public enum TargetType {
        SELF, SINGLE_OPPONENT, ALL_OPPONENT, DECK_SINGLE_OPPONENT, SINGLE_ALLIED, ALL_ALLIED, DECK_SINGLE_ALLIED
    };

    @Getter
    @Setter
    private TargetType targetType;

    @Getter
    private final List<ActionEffect> actionEffects = new ArrayList<>();

    @Getter
    private final Map<String, Integer> resourcesUsed = new HashMap<>();

    @Getter
    @Setter
    private int cooldown;

    @Getter
    @Setter
    private int time;
}
