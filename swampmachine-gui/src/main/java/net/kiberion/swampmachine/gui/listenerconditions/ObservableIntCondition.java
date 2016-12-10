package net.kiberion.swampmachine.gui.listenerconditions;

import org.apache.commons.lang3.Validate;

import net.kiberion.swampmachine.api.common.Condition;
import net.kiberion.swampmachine.subscription.ObservableInt;

public class ObservableIntCondition implements Condition {

    public enum ComparisonOperator {
        EQUALS, LARGER_THAN, LESS_THAN, LARGER_OR_EQUALS, LESS_OR_EQUALS, NOT_EQUALS
    }

    private final ObservableInt comparedValue;
    private final ComparisonOperator comparisonOperator;
    private final int compareTo;

    public ObservableIntCondition(ObservableInt comparedValue, int compareTo, ComparisonOperator comparisonOperator) {
        Validate.notNull(comparedValue);
        Validate.notNull(comparisonOperator);
        this.comparedValue = comparedValue;
        this.comparisonOperator = comparisonOperator;
        this.compareTo = compareTo;
    }

    @Override
    public boolean isSatisfied() {
        int comparedInt = comparedValue.getValue();
        switch (comparisonOperator) {
        case EQUALS:
            return comparedInt == compareTo;
        case LARGER_THAN:
            return comparedInt > compareTo;
        case LESS_THAN:
            return comparedInt < compareTo;
        case LARGER_OR_EQUALS:
            return comparedInt >= compareTo;
        case LESS_OR_EQUALS:
            return comparedInt <= compareTo;
        case NOT_EQUALS:
            return comparedInt != compareTo;
        default:
            throw new IllegalStateException("This shouldn't happen.");
        }
    }

}
