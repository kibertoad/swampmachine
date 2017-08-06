package net.kiberion.swampmachine.gui.elements.containers;

import com.badlogic.gdx.scenes.scene2d.Group;

import net.kiberion.swampmachine.annotations.InjectTransformedProperty;
import net.kiberion.swampmachine.annotations.NodeId;
import net.kiberion.swampmachine.api.elements.ButtonContainer;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.elements.Selectable;
import net.kiberion.swampmachine.api.elements.SelectableButtonEntry;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.gui.annotations.ElementBlueprint;
import net.kiberion.swampmachine.gui.annotations.ElementTransformedProperty;
import net.kiberion.swampmachine.gui.composer.transformers.ScriptTransformer;
import net.kiberion.swampmachine.gui.elements.SwampImageButton;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.gui.listeners.onclick.ToggleSelectionListener;
import net.kiberion.swampmachine.gui.observers.ButtonContainerUpdatingObserver;
import net.kiberion.swampmachine.invokables.ScriptInvokable;
import net.kiberion.swampmachine.subscription.ObservableSelectableButtonEntrySource;

@ElementBlueprint(id = "swCheckButtonList")
@ElementTransformedProperty(sourceProperty = "buttonSource", targetTransformer = ScriptTransformer.class)
public class CheckButtonList extends Group implements ButtonContainer {

    private boolean hasObserver;

    private ViewInfo selectedIcon;
    private ViewInfo unselectedIcon;

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

        ObservableSelectableButtonEntrySource entrySource = buttonSource.invoke();

        for (SelectableButtonEntry buttonEntry : entrySource.getValue()) {
            addButton(buttonEntry);
        }
        entrySource.addObserver(new ButtonContainerUpdatingObserver(this));
        hasObserver = true;
    }
    
    @NodeId(ids = {"selectedIcon"})
    @InjectTransformedProperty
    public void setSelectedIcon(ViewInfo selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    @NodeId(ids = {"unselectedIcon"})
    @InjectTransformedProperty
    public void setUnselectedIcon(ViewInfo unselectedIcon) {
        this.unselectedIcon = unselectedIcon;
    }

    @Override
    public void addButton(ButtonEntry buttonEntry) {
        SwampTextButton<?> button = new SwampTextButton<>(buttonEntry);
        button.setPosition(getLabelPosition());
        this.addActor(button);

        if (buttonEntry instanceof Selectable) {
            Selectable selectable = (Selectable) buttonEntry;
            SwampImageButton selectButton = new SwampImageButton(
                    getViewInfo((selectable).isSelected()).getImage(),
                    getViewInfo((selectable).isSelected()).getPicture());
            selectButton.setPosition(getSelectionIconPosition());
            selectButton.addListener(new ToggleSelectionListener(selectable));
            
            this.addActor(selectButton);
        }
    }

    protected ViewInfo getViewInfo(boolean isSelected) {
        return isSelected ? selectedIcon : unselectedIcon;
    }

    @Override
    public void clear() {
        clearChildren();
    }

    protected Position getLabelPosition() {
        return new CommonPosition(unselectedIcon.getImage().getRegionWidth() + 5, 0);
    }

    protected Position getSelectionIconPosition() {
        return new CommonPosition(0, 0);
    }

}
