package net.kiberion.swampmachine.gui.listenerconditions;

/**
 * @author kibertoad
 */
public class BooleanProviderCondition implements ListenerCondition {

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
