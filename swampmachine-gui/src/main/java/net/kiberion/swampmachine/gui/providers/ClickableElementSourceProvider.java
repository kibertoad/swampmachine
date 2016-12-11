package net.kiberion.swampmachine.gui.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.api.invokables.LambdaInvokable;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.elements.CommonButtonEntry;
import net.kiberion.swampmachine.subscription.ObservableButtonEntrySource;

/**
 * List with a one-off onClick effect
 * @author kibertoad
 *
 */
public abstract class ClickableElementSourceProvider<T extends MetadataHolderBlock> {

    /**
     * Filled list of elements that is exposed for the View to consume
     */
    @Getter
    private final ObservableButtonEntrySource buttonSource = new ObservableButtonEntrySource();
    
    
    public void fillButtonList() {
        List<ButtonEntry> buttonList = new ArrayList<>();
        for (T element : getElementList()) {
            addButton(buttonList, element);
        }

        buttonSource.setValue(buttonList);
    }
    
    protected void addButton(List<ButtonEntry> targetList, T sourceElement) {
        CommonButtonEntry buttonEntry = new CommonButtonEntry();
        setButtonText(sourceElement, buttonEntry);
        setButtonOnClickEffect(sourceElement, buttonEntry);
        targetList.add(buttonEntry);
    }
    
    protected String initButtonText(T sourceElement) {
        return sourceElement.getName();        
    }
    
    protected abstract LambdaInvokable initOnClickEffect(T sourceElement);
    
    public abstract Collection<T> getElementList();

    private void setButtonText(T sourceElement, CommonButtonEntry buttonEntry) {
        buttonEntry.setText(initButtonText(sourceElement));
    }
    
    private void setButtonOnClickEffect(T sourceElement, CommonButtonEntry buttonEntry) {
        Invokable onClickEffect = initOnClickEffect (sourceElement);
        buttonEntry.setOnClickEffect(onClickEffect);
    }
    
}
