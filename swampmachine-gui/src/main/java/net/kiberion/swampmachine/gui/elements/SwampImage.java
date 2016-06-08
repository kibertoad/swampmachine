package net.kiberion.swampmachine.gui.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SwampImage extends Image {

    public TextureRegion originalImage;
    public Pixmap picture;

    public float getTopY (){
        return getImageY()+ getHeight();
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
        this.setImage (new TextureRegionDrawable(image));
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

    public boolean isTransparent(int x, int y) {
        if (picture != null) {

            y = (int) (this.getImageHeight() - y);

            //Gdx.app.log("debug", "Hover Coords: "+x+"/"+y);

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

        //Gdx.app.log("debug", "Hit area: " + (int) x + "/" + (int) y);

        if ((!isTransparent((int) x, (int) y))) {
            return result;
        } else return null;
    }

    public void init() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void move (float deltaX, float deltaY) {
        this.setPosition(getX() + deltaX, getY() + deltaY);
    }

    @Deprecated
    public void setAlpha(float toValue) {
        Color c = getColor();
        c.a = toValue;

        setColor(c);
    }



}