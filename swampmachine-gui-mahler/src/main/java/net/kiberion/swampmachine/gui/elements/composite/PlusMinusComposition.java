package net.kiberion.swampmachine.gui.elements.composite;

import com.badlogic.gdx.scenes.scene2d.Group;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.gui.annotations.ElementBlueprint;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.composer.transformers.BoundLabelTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.ButtonTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.LabelTransformer;
import net.kiberion.swampmachine.gui.elements.SwampImage;
import net.kiberion.swampmachine.gui.elements.SwampLabel;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;

/**
 * ToDo find a universal way to attach to view's stage
 * 
 * @author kibertoad
 *
 */

@ElementBlueprint(id = "swPlusMinusComposition")
@ElementTransformedProperty(sourceProperty = "plus", targetTransformer = ButtonTransformer.class)
@ElementTransformedProperty(sourceProperty = "minus", targetTransformer = ButtonTransformer.class)
@ElementTransformedProperty(sourceProperty = "labelValue", targetTransformer = BoundLabelTransformer.class)
@ElementTransformedProperty(sourceProperty = "labelText", targetTransformer = LabelTransformer.class)
public class PlusMinusComposition extends Group {

    protected SwampImage numberBackground;
    protected SwampLabel numberLabel;
    protected SwampLabel textLabel;
    protected SwampTextButton<?> plusButton;
    protected SwampTextButton<?> minusButton;

    @NodeId(ids = {"plus"})
    @InjectTransformedProperty
    public void addPlusButton(SwampTextButton<?> button) {
        this.plusButton = button;
        this.plusButton.setPosition(getPlusPosition());
        this.addActor(button);
    }

    @NodeId(ids = {"minus"})
    @InjectTransformedProperty
    public void addMinusButton(SwampTextButton<?> button) {
        this.minusButton = button;
        this.minusButton.setPosition(getMinusPosition());
        this.addActor(button);
    }

    @NodeId(ids = {"labelValue"})
    @InjectTransformedProperty
    public void addValueLabel(SwampLabel label) {
        this.numberLabel = label;
        this.numberLabel.setPosition(getValuePosition());
        this.addActor(label);
    }
    
    @NodeId(ids = {"labelText"})
    @InjectTransformedProperty
    public void addTextLabel(SwampLabel label) {
        this.numberLabel = label;
        this.numberLabel.setPosition(getTextPosition());
        this.addActor(label);
    }
    

    protected Position getPlusPosition() {
        return new CommonPosition (40, 30);
    }

    protected Position getMinusPosition() {
        return new CommonPosition (0, 30);
    }
    
    protected Position getValuePosition() {
        return new CommonPosition (20, 30);
    }
    
    protected Position getTextPosition() {
        return new CommonPosition (20, 0);
    }
    
}
