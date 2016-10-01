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
    
    @NodeId (id = "buttonSource")
    @InjectTransformedProperty
    public void addButtons(ScriptInvokable buttonSource) {
        ObservableButtonEntrySource entrySource = buttonSource.invoke();
        
        setButtons (entrySource);
        entrySource.addObserver(new ButtonContainerUpdatingObserver(this));        
    }

    protected void setButtons(ObservableButtonEntrySource entrySource) {
        for (ButtonEntry buttonEntry : entrySource.getValue()) {
            addButton (buttonEntry);
        }
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
