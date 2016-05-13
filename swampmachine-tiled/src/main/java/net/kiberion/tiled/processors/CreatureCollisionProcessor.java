package net.kiberion.tiled.processors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.math.Rectangle;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.aspects.api.CollidableEntitiesSource;
import net.kiberion.tiled.aspects.holders.MapMetadataHolderAspect;
import net.kiberion.tiled.model.TiledMapInfo;

@Component
public class CreatureCollisionProcessor<TModel extends AbstractTiledMapModel<? extends MapMetadataHolderAspect> & CollidableEntitiesSource>
        implements CollisionInfoProvider {

    private static final Logger log = LogManager.getLogger();

    private TiledMapInfo mapInfo;

    @Autowired
    private TModel mapModel;

    public TModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(TModel mapModel) {
        this.mapModel = mapModel;
    }

    public TiledMapInfo getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(TiledMapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }

    public CollidableAspect getCreatureForPosition(CollidableAspect agent) {
        return getCreatureForPosition(agent, agent.getPositionAspect());
    }

    public CollidableAspect getCreatureForPosition(CollidableAspect agent,
            PositionAspect position) {
        Rectangle rectangle = new Rectangle(position.getX(), position.getY(),
                agent.getFormAspect().getWidthInTiles(),
                agent.getFormAspect().getHeightInTiles());

        for (CollidableAspect creature : mapModel.getCollidableEntities()) {
            if (creature.getFormAspect().getRectangle().overlaps(rectangle)
                    && agent.canCollide(creature) && creature.canCollide(agent)) {
                return creature;
            }
        }
        return null;
    }

    @Override
    public boolean isOccupiedByCollidable(int x, int y) {
        Rectangle rectangle = new Rectangle(x, y, 1, 1);

        for (CollidableAspect creature : mapModel.getCollidableEntities()) {
            if (creature.getFormAspect().getRectangle().overlaps(rectangle)) {
                return true;
            }
        }
        return false;
    }

}
