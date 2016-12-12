package net.kiberion.swampmachine.api.elements;

import java.util.Collection;

public interface LabelContainer {

    public default void setLabels(Collection<TextEntry> entrySource) {
        clear();
        if (entrySource != null) {
            for (TextEntry textEntry : entrySource) {
                addLabel(textEntry);
            }
        }
    }

    public void addLabel(TextEntry textEntry);

    public void clear();

}
