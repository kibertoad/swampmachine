package net.kiberion.swampmachine.gui.elements;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.InjectProperty;
import net.kiberion.swampmachine.gui.annotations.InjectTransformedProperty;

/**
 *
 * @author kibertoad
 */

@ElementPrototype(id = "swTable", supportedProperties = {})
public class SwampTable<T extends MetadataHolderBlock> extends Table {

    public boolean isVertical = true;
    public int filledElements = 0;
    public float OPTION_SPACING = 20f;

    public List<SwampTextButton<T>> textButtons = new ArrayList<>();

    public SwampTable() {
        super(UiManager.instance().getDefaultSkin());
    }

    public SwampTable(int setX, int setY) {
        super(UiManager.instance().getDefaultSkin());
        this.setPosition(setX, setY);
    }

    public SwampTextButton<T> addButton(String setText, T linkedNode) {
        SwampTextButton<T> result = addButton(setText);
        result.linkedNode = linkedNode;

        return result;
    }

    @Override
    public void clear() {
        super.clear();
        textButtons.clear();
    }

    public SwampTextButton<T> addButtonHorizontally(String text) {
        SwampTextButton<T> button = new SwampTextButton<>(text);
        add(button).space(OPTION_SPACING);

        textButtons.add(button);
        return button;
    }

    @InjectProperty(id = "textButtons", methodProperties = "text")
    public SwampTextButton<T> addButton(String text) {
        SwampTextButton<T> button = new SwampTextButton<>(text);
        return addButton (button);
    }
    
    @NodeId (id = "buttons")
    @InjectTransformedProperty
    public void addButtons(Collection <SwampTextButton<T>> buttons) {
        for (SwampTextButton<T> button : buttons) {
            addButton (button);
        }
    }
    
    public SwampTextButton<T> addButton(SwampTextButton<T> button) {
        add(button).space(OPTION_SPACING);
        row();

        textButtons.add(button);
        return button;
    }

    @Override
    public void setPosition(float toX, float toY) {
        super.setPosition(toX, toY);
    }

    public void restartFilling() {
        filledElements = 0;
    }

    public void complete() {
        Actor[] items = getChildren().begin();
        for (int i = 0, n = getChildren().size; i < n; i++) {
            Actor item = items[i];

            if (i >= filledElements) {
                item.setVisible(false);
            }

        }
        getChildren().end();
    }

    public void setVerticalList() {
        if (this.getChildren().size > 0) {
            this.setBounds(this.getX(), this.getY(), this.getChildren().get(0).getWidth(), 1000);
        }
    }

    public Label addLabel(String name) {
        Label result = new Label(name, UiManager.instance().getDefaultSkin());
        this.add(result);
        return result;
    }

}
