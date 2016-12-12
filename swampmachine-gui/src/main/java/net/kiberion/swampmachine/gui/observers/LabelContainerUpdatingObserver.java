package net.kiberion.swampmachine.gui.observers;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.Validate;

import net.kiberion.swampmachine.api.elements.LabelContainer;
import net.kiberion.swampmachine.api.elements.TextEntry;

public class LabelContainerUpdatingObserver implements Observer {

    private final LabelContainer container;

    public LabelContainerUpdatingObserver(LabelContainer container) {
        this.container = container;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable observableSource, Object newValue) {
        Validate.isInstanceOf(Collection.class, newValue);
        container.setLabels((Collection<TextEntry>) newValue);
    }

}
