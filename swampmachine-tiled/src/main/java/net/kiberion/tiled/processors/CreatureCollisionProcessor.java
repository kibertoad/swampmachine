package net.kiberion.tiled.processors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.math.Rectangle;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.kiberion.entities.map.api.Position;
import net.kiberion.tiled.aspects.holders.MapMetadataHolderAspect;
import net.kiberion.tiled.aspects.interfaces.CollidableAspect;
import net.kiberion.tiled.model.GenericTiledMapModel;
import net.kiberion.tiled.model.TiledMapInfo;

@Singleton
public class CreatureCollisionProcessor<TModel extends GenericTiledMapModel<? extends MapMetadataHolderAspect>> {

    private static final Logger log = LogManager.getLogger();

    private TiledMapInfo mapInfo;
    
    @Inject
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

    public CollidableAspect getCreatureForPosition(CollidableAspect agent, Position position) {

        Rectangle rectangle = new Rectangle (position.getX(), position.getY(), agent.getFormAspect().getWidthInTiles(), agent.getFormAspect().getHeightInTiles());
        
        for (CollidableAspect creature : mapModel.getCreatures()) {
            if (creature.getFormAspect().getRectangle().overlaps(rectangle)
                    && agent.canCollide(creature) && creature.canCollide(agent)) {
                return creature;
            }
        }
        return null;
    }
    
    
}
