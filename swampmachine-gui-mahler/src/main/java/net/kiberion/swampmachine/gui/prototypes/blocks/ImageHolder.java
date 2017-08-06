package net.kiberion.swampmachine.gui.prototypes.blocks;

public interface ImageHolder extends ImageBlock{

    @Override
    default String getImageId() {
        return getImageBlock().getImageId();
    }

    ImageBlock getImageBlock();

}
