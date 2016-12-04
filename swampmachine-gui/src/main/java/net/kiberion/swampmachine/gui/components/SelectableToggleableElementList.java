package net.kiberion.swampmachine.gui.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.api.elements.SelectableButtonEntry;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.elements.CommonButtonEntry;
import net.kiberion.swampmachine.gui.elements.CommonSelectableButtonEntry;
import net.kiberion.swampmachine.gui.invokables.PutObjectIntoMapInvokable;
import net.kiberion.swampmachine.gui.invokables.SetObservableObjectInvokable;
import net.kiberion.swampmachine.subscription.ObservableObject;
import net.kiberion.swampmachine.subscription.ObservableSelectableButtonEntrySource;

/**
 * Example usage: trait list where you can both select trait to see its
 * description and select/delect it for your character
 * 
 * @author kibertoad
 *
 */
public abstract class SelectableToggleableElementList<T extends MetadataHolderBlock> {

    public abstract Collection<T> getElementList();
    private final Map<T, Boolean> selectedElements = new HashMap<>(); // toggled elements

    // element selected for display, but not yet toggled
    @Getter
    private ObservableObject<T> selectedElement = new ObservableObject<>();

    /**
     * Fills button list anew based on addButton logic
     */
    public void fillButtonList() {
        List<SelectableButtonEntry> buttonList = new ArrayList<>();
        for (T element : getElementList()) {
            addButton(buttonList, element);
        }

        buttonSource.setValue(buttonList);
    }

    /**
     * Define and add new button based on source element data
     * 
     * @param targetList
     * @param sourceElement
     */
    protected void addButton(List<SelectableButtonEntry> targetList, T sourceElement) {
        CommonSelectableButtonEntry buttonEntry = new CommonSelectableButtonEntry();
        setButtonText(sourceElement, buttonEntry);
        setButtonOnClickEffect(sourceElement, buttonEntry);
        setButtonSelected (sourceElement, buttonEntry);
        setButtonSelectionEffects(sourceElement, buttonEntry);
        targetList.add(buttonEntry);
    }

    protected void setButtonText(T sourceElement, CommonButtonEntry buttonEntry) {
        buttonEntry.setText(sourceElement.getName());
    }
    
    protected void setButtonSelected (T sourceElement, CommonSelectableButtonEntry buttonEntry) {
        Boolean isEnabled = selectedElements.get(sourceElement);
        buttonEntry.setSelected(isEnabled != null && isEnabled);
    }

    protected void setButtonSelectionEffects(T sourceElement, CommonSelectableButtonEntry buttonEntry) {
        Invokable onSelectEffect = new PutObjectIntoMapInvokable<>(selectedElements, sourceElement, Boolean.TRUE);
        Invokable onDeselectEffect = new PutObjectIntoMapInvokable<>(selectedElements, sourceElement, Boolean.FALSE);

        buttonEntry.getOnSelectEffects().add(onSelectEffect);
        buttonEntry.getOnSelectEffects().add(new InvalidateList());
        
        buttonEntry.getOnDeselectEffects().add(onDeselectEffect);
        buttonEntry.getOnDeselectEffects().add(new InvalidateList());
    }

    protected void setButtonOnClickEffect(T sourceElement, CommonButtonEntry buttonEntry) {
        Invokable onClickEffect = new SetObservableObjectInvokable<>(selectedElement, sourceElement);
        buttonEntry.setOnClickEffect(onClickEffect);
    }

    /**
     * Filled list of elements that is exposed for the View to consume
     */
    @Getter
    private final ObservableSelectableButtonEntrySource buttonSource = new ObservableSelectableButtonEntrySource();
    
    
    protected class InvalidateList implements Invokable {
        @Override
        public <Y> Y invoke() {
            fillButtonList ();
            return null;
        }
        
    }

}
