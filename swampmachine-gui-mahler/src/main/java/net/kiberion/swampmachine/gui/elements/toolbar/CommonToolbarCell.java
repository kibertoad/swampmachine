package net.kiberion.swampmachine.gui.elements.toolbar;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

import lombok.Getter;
import net.kiberion.swampmachine.api.common.ParametrizedRecalculable;
import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;
import net.kiberion.swampmachine.gui.elements.SwampImage;
import net.kiberion.swampmachine.gui.elements.SwampLabel;

public class CommonToolbarCell<T extends IdHolderBlock> extends Group
        implements ParametrizedRecalculable<T>, IdHolderBlock {

    private final SwampImage entityIcon;
    private final SwampLabel entityText;

    @Getter
    private final String entityKey;

    public CommonToolbarCell(String entityID, TextureRegion image, String text) {
        if (image != null) {
            entityIcon = new SwampImage(image);
            this.addActor(entityIcon);
        } else {
            entityIcon = null;
        }
        entityText = new SwampLabel(text);
        entityKey = entityID;
        this.addActor(entityText);
    }

    @Override
    public void update(T updatedEntity) {
    }

    @Override
    public String getId() {
        return entityKey;
    }

    @Override
    public String toString() {
        return super.toString() + "; cell coords x:" + getX() + " y:" + getY();
    }

    public void makeDraggable() {
        entityIcon.makeDraggable();
    }
    
    protected SwampImage getEntityIcon() {
        return entityIcon;
    }
    
    public void setImageTouchable (boolean value) {
        entityIcon.setTouchable(value);
    }
}
