package net.kiberion.swampmachine.gui.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.gui.annotations.ElementPrototype;

@ElementPrototype(id = "swImage", supportedProperties = { "image" }, constructorProperties = { "image" })
public class SwampImage extends Image {

    public TextureRegion originalImage;
    public Pixmap picture;

    private float preDragX;
    private float preDragY;

    public float getTopY() {
        return getImageY() + getHeight();
    }

    public SwampImage(TextureRegion region, Pixmap setPicture) {
        super(region);

        originalImage = region;
        picture = setPicture;

        setTouchable(true);
    }

    public SwampImage(TextureRegion region) {
        super(region);

        originalImage = region;
        picture = null;

        setTouchable(false);
    }

    public SwampImage(AtlasRegion region) {
        this((TextureRegion) region);
    }

    public SwampImage(NinePatch ninePatch) {
        super(ninePatch);

        originalImage = null;
        picture = null;

        setTouchable(false);
    }

    public void setTouchable(boolean toValue) {
        if (toValue) {
            this.setTouchable(Touchable.enabled);
        } else {
            this.setTouchable(Touchable.disabled);
        }
    }

    public void setImage(TextureRegion image) {
        this.originalImage = image;
        this.setImage(new TextureRegionDrawable(image));
    }

    public void setImage(TextureRegion image, Pixmap pixmap) {
        setImage(image);
        this.picture = pixmap;
    }

    public void setImage(TextureRegionDrawable image) {
        this.setDrawable(image);

        if (image != null) {
            this.setHeight(image.getRegion().getRegionHeight());
            this.setWidth(image.getRegion().getRegionWidth());
        }
    }

    /*
    public boolean isTransparent(int x, int y) {
        if (picture != null) {

            y = (int) (this.getImageHeight() - y);

            // Gdx.app.log("debug", "Hover Coords: "+x+"/"+y);

            int pixel = picture.getPixel(x, y);
            pixel &= 0x000000ff;
            return pixel == 0;
        }
        return false;
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor result;
        result = super.hit(x, y, touchable);

        if (result == null) {
            return null;
        }

        // Gdx.app.log("debug", "Hit area: " + (int) x + "/" + (int) y);

        if ((!isTransparent((int) x, (int) y))) {
            return result;
        } else
            return null;
    }
    */

    public void init() {
    }

    public void move(float deltaX, float deltaY) {
        this.setPosition(getX() + deltaX, getY() + deltaY);
    }

    @Deprecated
    public void setAlpha(float toValue) {
        Color c = getColor();
        c.a = toValue;

        setColor(c);
    }

    public void setPosition(Position position) {
        setPosition(position.getX(), position.getY());
    }

    public void makeDraggable() {
        addListener(new DragListener() {

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                super.dragStart(event, x, y, pointer);
                preDragX = SwampImage.this.getX();
                preDragY = SwampImage.this.getY();
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                super.dragStop(event, x, y, pointer);
                SwampImage.this.setX(preDragX);
                SwampImage.this.setY(preDragY);
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
            }
        });
    }

}
