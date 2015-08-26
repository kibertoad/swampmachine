package net.kiberion.arcade.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.mvc.views.AbstractTiledMapView;
import net.kiberion.tiled.aspects.api.CollidableAspect;
import net.kiberion.tiled.aspects.api.FormAspect;
import net.kiberion.tiled.aspects.holders.MapMetadataHolderAspect;
import net.kiberion.tiled.aspects.impl.WallCollidableAspect;
import net.kiberion.tiled.camera.TiledMapCamera;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.processors.CreatureCollisionProcessor;
import net.kiberion.tiled.processors.WallObstacleProcessor;

@Singleton
public class ArcadeMapController<TModel extends AbstractTiledMapModel<? extends MapMetadataHolderAspect>> {

    private static final Logger log = LogManager.getLogger();
    private final CollidableAspect wallCollision = new WallCollidableAspect();
    
    @Inject
    protected WallObstacleProcessor wallObstacleProcessor;
    
    @Inject
    protected CreatureCollisionProcessor<TModel> creatureCollisisionProcessor;
    
    //Needs binding code like binder.bind(new TypeLiteral<AbstractTiledMapView<MapModel>>(){}).to(MapView.class);
    @Inject
    protected AbstractTiledMapView<TModel> view;

    public void setMap(TiledMapInfo mapInfo) {
        wallObstacleProcessor.setMapInfo(mapInfo);
        creatureCollisisionProcessor.setMapInfo(mapInfo);
    }
    
    public boolean isCollided (CollidableAspect entity, Position position, FormAspect formAspect) {
        if (wallObstacleProcessor.objectOverlapsObstacle(position, formAspect)) {
            entity.processCollision(wallCollision);
            log.info("Wall obstacle");
            return true;
        } 
        
        
        CollidableAspect collisionObject = creatureCollisisionProcessor.getCreatureForPosition(entity, position); 
        if (collisionObject != null) {
            entity.processCollision(collisionObject);
            log.info("Object obstacle");
            return true;
        } 

        return false;
     }    

    public boolean moveCreature(CollidableAspect entity, float deltaX, float deltaY) {
        log.info("Trying to move creature from point: "+entity.getPositionAspect());
        log.info("Delta: "+deltaX+"/"+deltaY);
        
        if (!isCollided (entity, entity.getPositionAspect().produceCloneWithAppliedDelta(deltaX, deltaY), entity.getFormAspect())) {
            performMove (entity, deltaX, deltaY);
            log.info("Moved successfully: "+entity.getPositionAspect() );
            //log.info("Camera: "+getCamera().getPosition() );
            return true;
        }
        return false;
    }
    

    public boolean moveCreature(CollidableAspect entity, PositionAspect delta) {
        return moveCreature (entity, delta.getX(), delta.getY());
    }
    
    
    public void centerCameraOnPlayer() {
        view.centerCameraOnPlayer();
    }
    
    public TiledMapCamera getCamera() {
        return view.getCamera();
    }

    
    private void performMove (CollidableAspect entity, float deltaX, float deltaY) {
        entity.getPositionAspect().applyDelta(deltaX, deltaY);
        entity.getFormAspect().moveRectangle(entity.getPositionAspect());
        view.updateTextureMapObjectPosition(entity, entity.getPositionAspect());
    }
    

    
    public void removeEntityFromView (MetadataHolderAspect entity) {
        view.removeMapObject(entity);
    }
}
