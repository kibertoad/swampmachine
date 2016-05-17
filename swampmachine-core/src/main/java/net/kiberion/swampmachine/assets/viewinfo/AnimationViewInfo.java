package net.kiberion.swampmachine.assets.viewinfo;

import java.util.Objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 * @author kibertoad
 */
public class AnimationViewInfo extends CommonModelEntityDescriptor {

    public int columns; // how many columns are in file
    public int rows; // how many rows are in file
    public float frameDuration;
    public Animation animation;

    @Override
    public void setImage(String id) {
        animation = getAnimation(id, columns, rows, frameDuration);
    }

    public Animation getAnimation(String image, int imageColumns, int imageRows, float frameDuration) {
        TextureAtlas.AtlasRegion atlasRegion = UiManager.instance().getImage(image);
        Objects.requireNonNull(atlasRegion);

        TextureRegion[][] tmp = atlasRegion.split(atlasRegion.getRegionWidth() / imageColumns,
                atlasRegion.getRegionHeight() / imageRows);

        TextureRegion[] walkFrames = new TextureRegion[imageColumns * imageRows];
        int index = 0;
        for (int i = 0; i < imageRows; i++) {
            for (int j = 0; j < imageColumns; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        Animation animation = new Animation(frameDuration, walkFrames);
        return animation;
    }

}
