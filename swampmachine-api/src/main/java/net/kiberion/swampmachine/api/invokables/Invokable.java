package net.kiberion.swampmachine.api.invokables;

/**
 * @author caryoscelus
 */
@FunctionalInterface
public interface Invokable {
    public <T> T invoke();
}
