package net.kiberion.swampmachine.gui.elements.containers;

import com.badlogic.gdx.scenes.scene2d.Group;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.api.elements.ButtonContainer;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.composer.transformers.ScriptTransformer;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.gui.observers.ButtonContainerUpdatingObserver;
import net.kiberion.swampmachine.invokables.ScriptInvokable;
import net.kiberion.swampmachine.subscription.ObservableButtonEntrySource;

@ElementPrototype(id = "swCheckButtonList")
@ElementTransformedProperty(sourceProperty = "buttonSource", targetTransformer = ScriptTransformer.class)
public class CheckButtonList extends Group implements ButtonContainer{
    
    private boolean hasObserver;
    
    /**
     * Should only be invoked once to avoid depending on multiple sources
     * 
     * @param buttonSource Script that is expected to link to a controller method which will provide a buttonSource
     */
    @NodeId (id = "buttonSource")
    @InjectTransformedProperty
    public void setButtonSource(ScriptInvokable buttonSource) {
        if (hasObserver) {
            throw new IllegalStateException ("Container cannot be linked to multiple sources.");
        }
        
        ObservableButtonEntrySource entrySource = buttonSource.invoke();
        
        for (ButtonEntry buttonEntry : entrySource.getValue()) {
            addButton (buttonEntry);
        }        
        entrySource.addObserver(new ButtonContainerUpdatingObserver(this));
        hasObserver = true;
    }

    @Override
    public void addButton (ButtonEntry buttonEntry) {
        SwampTextButton<?> button = new SwampTextButton<> (buttonEntry);
        this.addActor(button);
    }
    
    @Override
    public void clear() {
        clearChildren();
    }
    
}
