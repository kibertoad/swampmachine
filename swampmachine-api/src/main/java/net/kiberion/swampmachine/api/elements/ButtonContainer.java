package net.kiberion.swampmachine.api.elements;

import java.util.Collection;

public interface ButtonContainer {

    public default void setButtons(Collection<ButtonEntry> entrySource) {
        clear();
        if (entrySource != null) {
            for (ButtonEntry buttonEntry : entrySource) {
                addButton(buttonEntry);
            }
        }
    }

    public void addButton(ButtonEntry buttonEntry);

    public void clear();

}
