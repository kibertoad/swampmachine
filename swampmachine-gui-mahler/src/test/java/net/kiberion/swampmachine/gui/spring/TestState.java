package net.kiberion.swampmachine.gui.spring;

import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.api.CompositionConsumer;
import net.kiberion.swampmachine.gui.api.Scene;

@BoundCompositions(compositions = { "test" })
public class TestState implements CompositionConsumer{

    
    
    @Override
    public <T extends Scene<?>> T getScene() {
        // TODO Auto-generated method stub
        return null;
    }

}
