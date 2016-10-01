package net.kiberion.swampmachine.subscription;

import java.util.ArrayList;
import java.util.List;

import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;

public class ObservableButtonEntrySource extends AbstractObservable<List<ButtonEntry>, List<ButtonEntry>> implements EntrySource<ButtonEntry>{

    private List<ButtonEntry> buttonEntries = new ArrayList<>();
    
    @Override
    protected void setValueInternal(List<ButtonEntry> newValue) {
        buttonEntries = new ArrayList<> (newValue);
    }

    @Override
    public List<ButtonEntry> getValue() {
        return buttonEntries;
    }
    
    @Override
    public List<ButtonEntry> getEntries() {
        return getValue();
    }

}
