package net.kiberion.swampmachine.gui.prototypes;

import net.kiberion.swampmachine.gui.prototypes.blocks.CommonLabelBlock;
import net.kiberion.swampmachine.gui.prototypes.blocks.LabelBlock;
import net.kiberion.swampmachine.gui.prototypes.blocks.LabelHolder;

public class TextButtonPrototype extends AbstractElementPrototype implements LabelHolder {

    private final LabelBlock label = new CommonLabelBlock();

    @Override
    public LabelBlock getLabelBlock() {
        return label;
    }
}
