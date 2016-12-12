package net.kiberion.swampmachine.gui.elements;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.api.elements.TextEntry;

public class CommonTextEntry implements TextEntry{

    @Getter
    @Setter
    private String text;

    public CommonTextEntry(String text) {
        setText(text);
    }
    
}
