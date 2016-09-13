package net.kiberion.swampmachine.gui.api;

public interface CompositionConsumer {

    public <T extends Scene<?>> T getScene();
    
}
