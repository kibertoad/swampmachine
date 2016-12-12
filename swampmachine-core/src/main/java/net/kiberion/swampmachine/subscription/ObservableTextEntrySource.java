package net.kiberion.swampmachine.subscription;

import java.util.ArrayList;
import java.util.List;

import net.kiberion.swampmachine.api.elements.TextEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;

public class ObservableTextEntrySource extends AbstractObservable<List<TextEntry>, List<TextEntry>> implements EntrySource<TextEntry>{

    private List<TextEntry> buttonEntries = new ArrayList<>();
    
    @Override
    protected void setValueInternal(List<TextEntry> newValue) {
        buttonEntries = new ArrayList<> (newValue);
    }

    @Override
    public List<TextEntry> getValue() {
        return buttonEntries;
    }
    
    @Override
    public List<TextEntry> getEntries() {
        return getValue();
    }

}
