package net.kiberion.swampmachine.gui.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.elements.CommonButtonEntry;
import net.kiberion.swampmachine.gui.invokables.SetObservableObjectInvokable;
import net.kiberion.swampmachine.subscription.ObservableButtonEntrySource;
import net.kiberion.swampmachine.subscription.ObservableObject;

/**
 * Example usage: trait list where you can both select trait to see its
 * description and select/delect it for your character
 * 
 * @author kibertoad
 *
 */
public abstract class SelectableToggleableElementList<T extends MetadataHolderBlock> {

    public abstract Collection<T> getElementList();

    @Getter
    private ObservableObject<T> selectedElement = new ObservableObject<>();

    /**
     * Fills button list anew based on addButton logic
     */
    public void fillButtonList() {
        List<ButtonEntry> buttonList = new ArrayList<>();
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
    protected void addButton(List<ButtonEntry> targetList, T sourceElement) {
        CommonButtonEntry buttonEntry = new CommonButtonEntry();
        setButtonText(sourceElement, buttonEntry);
        setButtonOnClickEffect(sourceElement, buttonEntry);
        targetList.add(buttonEntry);
    }

    protected void setButtonText(T sourceElement, CommonButtonEntry buttonEntry) {
        buttonEntry.setText(sourceElement.getName());
    }

    protected void setButtonOnClickEffect(T sourceElement, CommonButtonEntry buttonEntry) {
        Invokable onClickEffect = new SetObservableObjectInvokable<> (selectedElement, sourceElement);
        buttonEntry.setOnClickEffect(onClickEffect);
    }

    /**
     * Filled list of elements that is exposed for the View to consume
     */
    @Getter
    private final ObservableButtonEntrySource buttonSource = new ObservableButtonEntrySource();

}
