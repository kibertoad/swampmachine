package net.kiberion.swampmachine.assets.viewinfo;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 * @author: kibertoad
 */
public class ViewInfo extends CommonModelEntityDescriptor {

    @Setter
    @Getter
    private TextureRegionDrawable drawableImage;

    @Setter
    @Getter
    private TextureRegion image;

    @Setter
    @Getter
    private Pixmap picture;

    public ViewInfo() {
    }

    public ViewInfo(FileHandle entry) {
        image = UiManager.instance().atlas().findRegion(entry.nameWithoutExtension());
        drawableImage = new TextureRegionDrawable(image);
        picture = new Pixmap(entry);
    }
    
    @Override
    public String toString() {
        return getMetadata().getId();
    }
}
