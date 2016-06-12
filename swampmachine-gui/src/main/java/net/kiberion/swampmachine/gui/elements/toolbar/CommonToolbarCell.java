package net.kiberion.swampmachine.gui.elements.toolbar;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

import lombok.Getter;
import net.kiberion.entities.common.api.ParametrizedRecalculable;
import net.kiberion.swampmachine.entityblocks.api.EntityInstanceMetadataBlock;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.elements.SwampImage;
import net.kiberion.swampmachine.gui.elements.SwampLabel;

public class CommonToolbarCell<T extends MetadataHolderBlock> extends Group implements ParametrizedRecalculable<T>, MetadataHolderBlock{

    private final SwampImage entityIcon;
    private final SwampLabel entityText;
    
    @Getter
    private final String entityKey;
    
 
    public CommonToolbarCell (String entityID, TextureRegion image, String text) {
        if (image != null) {
            entityIcon = new SwampImage(image);
            this.addActor(entityIcon);
        } else {
            entityIcon = null;
        }
        entityText = new SwampLabel (text);
        entityKey = entityID;
        this.addActor(entityText);
    }
    
    @Override
    public void update(T updatedEntity) {
    }
    
    @Override
    public EntityInstanceMetadataBlock getMetadata() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setMetadata(EntityInstanceMetadataBlock metadata) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getId() {
        return entityKey;
    }
    
    @Override
    public String toString() {
        return super.toString()+"; cell coords x:"+getX()+" y:"+getY();
    }
}
