package net.kiberion.swampmachine.gui.elements.containers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.api.elements.LabelContainer;
import net.kiberion.swampmachine.api.elements.TextEntry;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.annotations.InjectProperty;
import net.kiberion.swampmachine.gui.composer.transformers.ScriptTransformer;
import net.kiberion.swampmachine.gui.elements.SwampLabel;
import net.kiberion.swampmachine.gui.observers.LabelContainerUpdatingObserver;
import net.kiberion.swampmachine.invokables.ScriptInvokable;
import net.kiberion.swampmachine.subscription.ObservableTextEntrySource;

/**
 *
 * @author kibertoad
 */

@ElementPrototype(id = "swLabelTable", supportedProperties = {})
@ElementTransformedProperty(sourceProperty = "labelSource", targetTransformer = ScriptTransformer.class)
public class SwampLabelTable<T extends MetadataHolderBlock> extends Table implements LabelContainer{

    private boolean hasObserver;

    private static final Logger log = LogManager.getLogger();
    
    public boolean isVertical = true;
    public int filledElements = 0;
    public float OPTION_SPACING = 20f;
    
    private float realY;

    public List<SwampLabel> textLabels = new ArrayList<>();

    public SwampLabelTable() {
        super(UiManager.instance().getDefaultSkin());
    }

    public SwampLabelTable(int setX, int setY) {
        this();
        this.setPosition(setX, setY);
    }

    @Override
    public void clear() {
        super.clear();
        textLabels.clear();
    }

    public SwampLabel addLabelHorizontally(String text) {
        SwampLabel label = new SwampLabel(text);
        add(label).space(OPTION_SPACING);

        textLabels.add(label);
        return label;
    }

    /**
     * Should only be invoked once to avoid depending on multiple sources
     * 
     * @param labelSource
     *            Script that is expected to link to a controller method which
     *            will provide a labelSource
     */
    @NodeId(ids = {"labelSource"})
    @InjectTransformedProperty
    public void setLabelSource(ScriptInvokable labelSource) {
        if (hasObserver) {
            throw new IllegalStateException("Container cannot be linked to multiple sources.");
        }

        ObservableTextEntrySource entrySource = labelSource.invoke();

        for (TextEntry labelEntry : entrySource.getValue()) {
            addLabel(labelEntry);
        }
        entrySource.addObserver(new LabelContainerUpdatingObserver(this));
        hasObserver = true;
    }    
    
    @Override
    public void addLabel(TextEntry labelEntry) {
        addLabel (new SwampLabel(labelEntry));
    }
    
    @InjectProperty(id = "labels", methodProperties = "text")
    public SwampLabel addLabel(String text) {
        SwampLabel label = new SwampLabel(text);
        return addLabel (label);
    }
    

    @NodeId (ids = {"label"})
    @InjectTransformedProperty
    public void addLabels(Collection <SwampLabel> labels) {
        for (SwampLabel label : labels) {
            addLabel (label);
        }
    }
    
    public SwampLabel addLabel(SwampLabel label) {
        add(label).space(OPTION_SPACING).left();
        row();
        pack();

        textLabels.add(label);
        updatePosition(getX());
        
        log.info("Added label to: "+label.getX(), "/"+label.getY());
        return label;
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



}
