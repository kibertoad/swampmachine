package net.kiberion.swampmachine.gui.spring;

import java.util.Collections;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.api.composition.CompositionConsumer;
import net.kiberion.swampmachine.gui.annotations.Bound;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.api.GenericScene;
import net.kiberion.swampmachine.gui.elements.SwampLabel;

@BoundCompositions(compositions = { "test" })
public class TestState implements CompositionConsumer{

    @Bound(id = "label")
    @Getter
    private SwampLabel label;
    
    private GenericScene scene = new GenericScene();
    
    
    @SuppressWarnings("unchecked")
    @Override
    public GenericScene getScene() {
        return scene;
    }


    @Override
    public Map<String, Object> getContext() {
        return Collections.emptyMap();
    }

}
