package net.kiberion.swampmachine.gui.elements.composite;

import com.badlogic.gdx.scenes.scene2d.Group;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.composer.transformers.ButtonTransformer;
import net.kiberion.swampmachine.gui.elements.SwampLabel;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;

@ElementPrototype(id = "swCheckButton")
@ElementTransformedProperty(sourceProperty = "button", targetTransformer = ButtonTransformer.class)
public class CheckButton extends Group{

    protected SwampLabel selectionLabel;
    protected SwampTextButton<?> button;

    private boolean isSelected = false;

    public CheckButton() {
        selectionLabel = new SwampLabel("-");
        this.addActor(selectionLabel);
    }
    
    @NodeId(ids = {"button"})
    @InjectTransformedProperty
    public void addPlusButton(SwampTextButton<?> button) {
        this.button = button;
        this.button.setPosition(getButtonPosition());
        this.addActor(button);
    }
    
    protected Position getButtonPosition() {
        return new CommonPosition (60, 30);
    }

    public void select() {
        if (!isSelected) {
            isSelected = true;
            selectionLabel.setText("+");
        }
    }

    public void deselect() {
        if (isSelected) {
            isSelected = false;
            selectionLabel.setText("-");
        }
    }

}
