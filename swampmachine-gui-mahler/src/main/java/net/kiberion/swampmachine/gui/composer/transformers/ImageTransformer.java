package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;
import net.kiberion.swampmachine.registries.ImageRegistry;

public class ImageTransformer extends AbstractTransformer<String, TextureRegion> {

    public static final String IMAGE_PARAMETER = "image";

    @Autowired
    private ImageRegistry imageRegistry;

    @Override
    public TextureRegion transformSingle(String parameter, Map<String, Object> context) {
        ViewInfo viewInfo = imageRegistry.getImages().get(parameter);
        Validate.notNull(viewInfo, "Unknown viewInfo: "+parameter);
        return viewInfo.getImage();
    }

    @Override
    public String getParameterName() {
        return IMAGE_PARAMETER;
    }

}
