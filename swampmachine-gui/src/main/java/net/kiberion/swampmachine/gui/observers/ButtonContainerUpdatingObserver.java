package net.kiberion.swampmachine.gui.observers;

import java.util.Observable;
import java.util.Observer;

import net.kiberion.swampmachine.api.elements.ButtonContainer;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;

public class ButtonContainerUpdatingObserver implements Observer{

    private final ButtonContainer container;

    public ButtonContainerUpdatingObserver(ButtonContainer container) {
        this.container = container;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable o, Object arg) {
        container.setButtons((EntrySource<ButtonEntry>) arg);
    }    
    
}
