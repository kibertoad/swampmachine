/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.assets.loaders.impl;

import net.kiberion.swampmachine.assets.UiManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.kiberion.swampmachine.assets.loaders.api.AbstractSyncLoader;
import net.kiberion.swampmachine.annotations.LoadBeforeStartup;
import net.kiberion.swampmachine.assets.viewinfo.EntityViewInfo;
import net.kiberion.swampmachine.registries.ImageRegistry;

/**
 * @author kibertoad
 */
@LoadBeforeStartup
public class ImageLoader extends AbstractSyncLoader {

    @Autowired
    private ImageRegistry imageRegistry;

    protected EntityViewInfo initNewEntity() {
        return new EntityViewInfo();
    }

    @Override
    public int getPriority() {
        return 10;
    }
    
    @Override
    public void load() {
        for (AtlasRegion region : UiManager.instance().atlas().getRegions()) {
            EntityViewInfo targetObject = new EntityViewInfo();
            String imageId = region.name;

            targetObject.setDrawableImage(new TextureRegionDrawable(region));
            targetObject.setImage(region);

            imageRegistry.getImages().put(imageId, targetObject);
        }
    }
}
