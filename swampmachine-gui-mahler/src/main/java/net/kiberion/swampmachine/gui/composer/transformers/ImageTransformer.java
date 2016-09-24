package net.kiberion.swampmachine.gui.composer.transformers;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.kiberion.swampmachine.registries.ImageRegistry;

public class ImageTransformer extends AbstractTransformer<String, TextureRegion> {

    public static final String IMAGE_PARAMETER = "image";

    @Autowired
    private ImageRegistry imageRegistry;

    @Override
    public TextureRegion transformSingle(String parameter) {
        return imageRegistry.getImages().get(parameter).getImage();
    }

    @Override
    public String getParameterName() {
        return IMAGE_PARAMETER;
    }

}
