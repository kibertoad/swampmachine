package net.kiberion.swampmachine.subscription;

import java.util.ArrayList;
import java.util.List;

import net.kiberion.swampmachine.api.elements.SelectableButtonEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;

public class ObservableSelectableButtonEntrySource extends AbstractObservable<List<SelectableButtonEntry>, List<SelectableButtonEntry>> implements EntrySource<SelectableButtonEntry>{

    private List<SelectableButtonEntry> buttonEntries = new ArrayList<>();
    
    @Override
    protected void setValueInternal(List<SelectableButtonEntry> newValue) {
        buttonEntries = new ArrayList<> (newValue);
    }

    @Override
    public List<SelectableButtonEntry> getValue() {
        return buttonEntries;
    }
    
    @Override
    public List<SelectableButtonEntry> getEntries() {
        return getValue();
    }

}
