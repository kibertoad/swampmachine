package net.kiberion.swampmachine.gui.elements.composite;

import com.badlogic.gdx.scenes.scene2d.Group;

import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.gui.composer.transformers.ButtonTransformer;
import net.kiberion.swampmachine.gui.elements.SwampImage;
import net.kiberion.swampmachine.gui.elements.SwampLabel;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;


/**
 * ToDo find a universal way to attach to view's stage
 * @author kibertoad
 *
 */

@ElementPrototype(id = "swPlusMinusComposition")
@ElementTransformedProperty(sourceProperty = "plus", targetTransformer = ButtonTransformer.class)
@ElementTransformedProperty(sourceProperty = "minus", targetTransformer = ButtonTransformer.class)
public class PlusMinusComposition extends Group{

    protected SwampImage numberBackground;
    protected SwampLabel numberLabel;
    protected SwampTextButton<?> plusButton;
    protected SwampTextButton<?> minusButton;
    
    @NodeId (id = "plus")
    @InjectTransformedProperty    
    public void addPlusButton (SwampTextButton<?> button) {
        this.plusButton = button;
        this.addActor(button);
    }
    
}
