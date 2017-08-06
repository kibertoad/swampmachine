package net.kiberion.swampmachine.gui.elements.containers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.kiberion.swampmachine.gui.annotations.ElementBlueprint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.api.elements.ButtonContainer;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.annotations.InjectProperty;
import net.kiberion.swampmachine.gui.composer.transformers.ScriptTransformer;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.gui.observers.ButtonContainerUpdatingObserver;
import net.kiberion.swampmachine.invokables.ScriptInvokable;
import net.kiberion.swampmachine.subscription.ObservableButtonEntrySource;

/**
 *
 * @author kibertoad
 */

@ElementBlueprint(id = "swTable", supportedProperties = {})
@ElementTransformedProperty(sourceProperty = "buttonSource", targetTransformer = ScriptTransformer.class)
public class SwampButtonTable<T extends MetadataHolderBlock> extends Table implements ButtonContainer{

    private boolean hasObserver;

    private static final Logger log = LogManager.getLogger();
    
    public boolean isVertical = true;
    public int filledElements = 0;
    public float OPTION_SPACING = 20f;
    
    private float realY;

    public List<SwampTextButton<T>> textButtons = new ArrayList<>();

    public SwampButtonTable() {
        super(UiManager.instance().getDefaultSkin());
    }

    public SwampButtonTable(int setX, int setY) {
        this();
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

    /**
     * Should only be invoked once to avoid depending on multiple sources
     * 
     * @param buttonSource
     *            Script that is expected to link to a controller method which
     *            will provide a buttonSource
     */
    @NodeId(ids = {"buttonSource"})
    @InjectTransformedProperty
    public void setButtonSource(ScriptInvokable buttonSource) {
        if (hasObserver) {
            throw new IllegalStateException("Container cannot be linked to multiple sources.");
        }

        ObservableButtonEntrySource entrySource = buttonSource.invoke();

        for (ButtonEntry buttonEntry : entrySource.getValue()) {
            addButton(buttonEntry);
        }
        entrySource.addObserver(new ButtonContainerUpdatingObserver(this));
        hasObserver = true;
    }    
    
    @Override
    public void addButton(ButtonEntry buttonEntry) {
        addButton (new SwampTextButton<>(buttonEntry));
    }

    @InjectProperty(id = "textButtons", methodProperties = "text")
    public SwampTextButton<T> addButton(String text) {
        SwampTextButton<T> button = new SwampTextButton<>(text);
        return addButton (button);
    }
    
    @NodeId (ids = {"buttons"})
    @InjectTransformedProperty
    public void addButtons(Collection <SwampTextButton<T>> buttons) {
        for (SwampTextButton<T> button : buttons) {
            addButton (button);
        }
    }
    
    public SwampTextButton<T> addButton(SwampTextButton<T> button) {
        add(button).space(OPTION_SPACING).left();
        row();
        pack();

        textButtons.add(button);
        updatePosition(getX());
        
        log.info("Added button to: "+button.getX(), "/"+button.getY());
        return button;
    }

    @Override
    public void setPosition(float leftX, float topY) {
        realY = topY;
        updatePosition(leftX);
    }
    
    public void updatePosition (float leftX) {
        super.setPosition (leftX, realY - this.getHeight());
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
