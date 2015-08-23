package net.kiberion.assets.viewinfo;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.kiberion.assets.UiManager;
import net.kiberion.entities.common.impl.DataNode;

/**
 * @author: kibertoad
 */
public class ViewInfo extends DataNode {

    public TextureRegionDrawable drawableImage;
    public TextureRegion image;
    public TextureRegion getImage() {
		return image;
	}

	public Pixmap picture;

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
