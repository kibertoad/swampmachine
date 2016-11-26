package net.kiberion.swampmachine.blueprints.arcade.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.mvc.views.AbstractTiledMapView;
import net.kiberion.swampmachine.blueprints.arcade.controller.api.CreatureMovementController;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entities.spatial.impl.CommonPosition;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.tiled.camera.TiledMapCamera;
import net.kiberion.tiled.entityblocks.api.CollidableBlock;
import net.kiberion.tiled.entityblocks.api.CollidableEntitiesSource;
import net.kiberion.tiled.entityblocks.api.FormBlock;
import net.kiberion.tiled.entityblocks.holders.MapMetadataHolderBlock;
import net.kiberion.tiled.entityblocks.impl.WallCollidableBlock;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.processors.CreatureCollisionProcessor;
import net.kiberion.tiled.processors.WallObstacleProcessor;

public class ArcadeMapController<TModel extends AbstractTiledMapModel<? extends MapMetadataHolderBlock> & CollidableEntitiesSource> implements CreatureMovementController{

    private static final Logger log = LogManager.getLogger();
    private final CollidableBlock wallCollision = new WallCollidableBlock();
    
    @Autowired
    protected WallObstacleProcessor wallObstacleProcessor;
    
    @Autowired
    protected CreatureCollisionProcessor<TModel> creatureCollisisionProcessor;
    
    @Autowired
    protected AbstractTiledMapView<TModel> view;

    public void setMap(TiledMapInfo mapInfo) {
        wallObstacleProcessor.setMapInfo(mapInfo);
        creatureCollisisionProcessor.setMapInfo(mapInfo);
    }
    
    public boolean isCollided (CollidableBlock entity, Position position, FormBlock formAspect) {
        if (wallObstacleProcessor.objectOverlapsObstacle(position, formAspect)) {
            entity.processCollision(wallCollision);
            log.info("Wall obstacle");
            return true;
        } 
        
        
        CollidableBlock collisionObject = creatureCollisisionProcessor.getCreatureForPosition(entity, position); 
        if (collisionObject != null) {
            entity.processCollision(collisionObject);
            log.info("Object obstacle");
            return true;
        } 

        return false;
     }    

    public boolean moveCreature(CollidableBlock entity, float deltaX, float deltaY) {
        //log.info("Trying to move creature from point: "+entity.getPositionAspect());
        //log.info("Delta: "+deltaX+"/"+deltaY);
        
        if (!isCollided (entity, entity.getPositionAspect().produceCloneWithAppliedDelta(deltaX, deltaY), entity.getFormAspect())) {
            performMove (entity, deltaX, deltaY);
            //log.info("Moved successfully: "+entity.getPositionAspect() );
            //log.info("Camera: "+getCamera().getPosition() );
            return true;
        }
        return false;
    }
    

    @Override
    public boolean moveCreature(CollidableBlock entity, CommonPosition delta) {
        return moveCreature (entity, delta.getX(), delta.getY());
    }
    
    
    public void centerCameraOnPlayer() {
        view.centerCameraOnPlayer();
    }
    
    public TiledMapCamera getCamera() {
        return view.getCamera();
    }

    
    private void performMove (CollidableBlock entity, float deltaX, float deltaY) {
        entity.getPositionAspect().applyDelta(deltaX, deltaY);
        entity.getFormAspect().moveRectangle(entity.getPositionAspect());
        view.updateTextureMapObjectPosition(entity, entity.getPositionAspect());
    }
    

    
    @Override
    public void removeEntityFromView (MetadataHolderBlock entity) {
        view.removeMapObject(entity);
    }
}
