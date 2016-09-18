package net.kiberion.swampmachine.api;

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

}
