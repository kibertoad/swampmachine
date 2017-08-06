package net.kiberion.swampmachine.gui.prototypes.blocks;

import net.kiberion.swampmachine.gui.composition.elements.api.Image;

public interface LabelHolder extends LabelBlock {

    @Override
    default String getText() {
        return getLabelBlock().getText();
    }

    LabelBlock getLabelBlock();
}
