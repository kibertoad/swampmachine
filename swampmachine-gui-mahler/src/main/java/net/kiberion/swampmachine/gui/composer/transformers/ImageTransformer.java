package net.kiberion.swampmachine.gui.composer.transformers;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.kiberion.swampmachine.gui.api.ParameterTransformer;
import net.kiberion.swampmachine.registries.ImageRegistry;

public class ImageTransformer implements ParameterTransformer{

    public static final String IMAGE_PARAMETER = "image";
    
    @Autowired
    private ImageRegistry imageRegistry;
    
    
    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <String, TextureRegion> TextureRegion transform(String parameter) {
        return (TextureRegion) imageRegistry.getImages().get(parameter).getImage();
    }

    @Override
    public Class<String> getInputClass() {
        return String.class;
    }

    @Override
    public Class<TextureRegion> getOutputClass() {
        return TextureRegion.class;
    }

    @Override
    public String getParameterName() {
        return IMAGE_PARAMETER;
    }

    
    
}
