package net.kiberion.swampmachine.gui.listenerconditions;

import java.util.ArrayList;

/**
 * @author kibertoad
 */
public class ListenerConditionContainer extends ArrayList<ListenerCondition> implements ListenerCondition {

    /**
     * 
     */
    private static final long serialVersionUID = -4894493025400399989L;

    @Override
    public boolean isSatisfied() {
        for (ListenerCondition condition: this) {
            if (!condition.isSatisfied()) {
                return false;
            }
        }

        return true;
    }
}
