package net.kiberion.swampmachine.api.elements;

import net.kiberion.swampmachine.api.sources.EntrySource;

public interface ButtonContainer {

    public default void setButtons(EntrySource<ButtonEntry> entrySource) {
        clear();
        if (entrySource != null) {
            for (ButtonEntry buttonEntry : entrySource.getEntries()) {
                addButton(buttonEntry);
            }
        }
    }

    public void addButton(ButtonEntry buttonEntry);

    public void clear();

}
