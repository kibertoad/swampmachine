package net.kiberion.swampmachine.factories.params;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;

/**
 * Simple implementation of {@link SpawnParams} interface that specifies what
 * and where should be spawned.
 * 
 * @author kibertoad
 *
 */
public class CommonSpawnParams implements SpawnParams {

    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private int x;

    @Setter
    @Getter
    private int y;

    public CommonSpawnParams() {
    }

    public CommonSpawnParams(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    public Position getPosition() {
        return new CommonPosition(x, y);
    }

}
