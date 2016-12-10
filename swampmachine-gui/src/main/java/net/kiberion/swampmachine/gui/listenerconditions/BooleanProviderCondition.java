package net.kiberion.swampmachine.gui.listenerconditions;

import net.kiberion.swampmachine.api.common.Condition;

/**
 * @author kibertoad
 */
public class BooleanProviderCondition implements Condition {

    private final BooleanProvider provider;
    private final boolean expectedValue;

    public BooleanProviderCondition(BooleanProvider provider, boolean expectedValue) {
        this.provider = provider;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean isSatisfied() {
        return expectedValue == provider.evaluate();
    }
}
