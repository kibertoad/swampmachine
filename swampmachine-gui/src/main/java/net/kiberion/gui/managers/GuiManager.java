package net.kiberion.gui.managers;

import java.util.Map;

import org.apache.commons.lang3.Validate;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.utils.ImmutableEnumMapBuilder;

public class GuiManager {

    public enum PositionCode {
        CENTER
    }
    
    //TODO replace with proper layouts later
    private static final Map<PositionCode, Position> positionMap = new ImmutableEnumMapBuilder<PositionCode, Position> (PositionCode.class)
            .put(PositionCode.CENTER, new CommonPosition(600, 500))
            .build();
    
    @Setter
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
    
    
}
