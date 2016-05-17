package net.kiberion.tiled.entityblocks.impl;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.UiManager;
import net.kiberion.tiled.entityblocks.api.ViewBlock;

public class CommonViewBlock implements ViewBlock{

    @Setter
    private TextureRegion image;
    
    @Getter
    @Setter
    private String imageID;
    
    @Override
    public TextureRegion getImage() {
        if (image == null) {
            UiManager.instance().getImage(imageID);
        }
        
        return image;
    }
    
}
