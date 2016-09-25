package net.kiberion.swampmachine.api;

import java.util.Map;

public interface CompositionConsumer {

    /**
     * 
     * @return Default scene
     */
    public <T> T getScene();

    public default <T> T getScene(String id) {
        return getScene();
    }

    public default void postInjection() {
    }

    /**
     * Return consumer-related values that might affect composition value transformations
     * @return
     */
    public Map<String, Object> getContext();

}
