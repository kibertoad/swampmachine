package net.kiberion.swampmachine.gui.observers;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import net.kiberion.swampmachine.gui.elements.SwampLabel;

public class LabelUpdatingObserver implements Observer {

    private final SwampLabel label;

    public LabelUpdatingObserver(SwampLabel label) {
        this.label = label;
    }

    @Override
    public void update(Observable o, Object arg) {
        label.setText(arg != null ? arg.toString() : StringUtils.EMPTY);
    }

}
