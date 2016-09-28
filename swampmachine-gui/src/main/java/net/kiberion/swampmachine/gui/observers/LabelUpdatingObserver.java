package net.kiberion.swampmachine.gui.observers;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import net.kiberion.swampmachine.api.elements.Label;

public class LabelUpdatingObserver implements Observer {

    private final Label label;

    public LabelUpdatingObserver(Label label) {
        this.label = label;
    }

    @Override
    public void update(Observable o, Object arg) {
        label.setText(arg != null ? arg.toString() : StringUtils.EMPTY);
    }

}
