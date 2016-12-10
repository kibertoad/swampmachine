package net.kiberion.swampmachine.gui.listenerconditions;

import java.util.ArrayList;

import net.kiberion.swampmachine.api.common.Condition;

/**
 * @author kibertoad
 */
public class ListenerConditionContainer extends ArrayList<Condition> implements Condition {

    /**
     * 
     */
    private static final long serialVersionUID = -4894493025400399989L;

    @Override
    public boolean isSatisfied() {
        for (Condition condition: this) {
            if (!condition.isSatisfied()) {
                return false;
            }
        }

        return true;
    }
}
