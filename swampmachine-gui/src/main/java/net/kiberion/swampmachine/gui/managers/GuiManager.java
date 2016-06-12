package net.kiberion.swampmachine.gui.managers;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import lombok.Getter;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.registries.CommonViewInfoRegistry;
import net.kiberion.swampmachine.registries.ImageRegistry;
import net.kiberion.swampmachine.utils.ImmutableEnumMapBuilder;

public class GuiManager {

    @SuppressWarnings("unused")
    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private CommonViewInfoRegistry viewInfoRegistry;
    
    @Autowired
    private ImageRegistry imageRegistry;
    
    public enum PositionCode {
        CENTER
    }
    
    //TODO replace with proper layouts later
    private static final Map<PositionCode, Position> positionMap = new ImmutableEnumMapBuilder<PositionCode, Position> (PositionCode.class)
            .put(PositionCode.CENTER, new CommonPosition(600, 500))
            .build();
    
    @Getter
    private Stage stage;

    public Table addTable (PositionCode positionCode) {
        Validate.notNull(positionCode);
        Position position = positionMap.get(positionCode);

        Table table = new Table();
        this.getStage().addActor(table);
        table.setPosition(position.getX(), position.getY());
        
        return table;
    }
    
    public void setStage(Stage stage) {
        Validate.isTrue(this.stage == null, "Stage was already set.");
        this.stage = stage;
    }
    
    public TextureRegion getImage (String imageCode) {
        return imageRegistry.getImages().get(imageCode).getImage();
    }

    public TextureRegion getImageForEntity(String entityId) {
        String imageId = viewInfoRegistry.getImageIdForEntity(entityId);
        ViewInfo viewInfo = imageRegistry.getImages().get(imageId);
        Validate.notNull(viewInfo, "No image for imageId: "+imageId);

        return viewInfo.getImage();
    }
    
}
