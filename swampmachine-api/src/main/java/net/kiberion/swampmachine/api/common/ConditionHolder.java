package net.kiberion.swampmachine.api.common;

import java.util.Collection;

public interface ConditionHolder {

    public Collection<Condition> getConditions();

    public default void addCondition(Condition condition) {
        getConditions().add(condition);
    }

    public default boolean isConditionsSatisfied() {
        for (Condition condition : getConditions()) {
            if (!condition.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

}
