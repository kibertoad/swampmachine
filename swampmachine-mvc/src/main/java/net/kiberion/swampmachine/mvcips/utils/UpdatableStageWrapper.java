package net.kiberion.swampmachine.mvcips.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;

import net.kiberion.entities.common.api.RealtimeUpdatable;

public class UpdatableStageWrapper implements RealtimeUpdatable{

    private final Stage stage;

    public UpdatableStageWrapper(Stage stage) {
        super();
        this.stage = stage;
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }
    
}
