package net.kiberion.swampmachine.gui.observers;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.Validate;

import net.kiberion.swampmachine.api.elements.ButtonContainer;
import net.kiberion.swampmachine.api.elements.ButtonEntry;

public class ButtonContainerUpdatingObserver implements Observer{

    private final ButtonContainer container;

    public ButtonContainerUpdatingObserver(ButtonContainer container) {
        this.container = container;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable observableSource, Object newValue) {
        Validate.isInstanceOf(Collection.class, newValue);
        container.setButtons((Collection<ButtonEntry>) newValue);
    }    
    
}
