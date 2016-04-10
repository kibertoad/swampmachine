package net.kiberion.mvc.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.kiberion.aspects.api.MetadataHolderAspect;
import net.kiberion.aspects.api.PositionHolderAspect;
import net.kiberion.assets.UiManager;
import net.kiberion.assets.viewinfo.CreatureViewInfo;
import net.kiberion.blueprint.common.registries.CommonViewInfoRegistry;
import net.kiberion.entities.map.api.Position;
import net.kiberion.entities.map.impl.PositionAspect;
import net.kiberion.mvc.StateViewBase;
import net.kiberion.mvc.model.AbstractTiledMapModel;
import net.kiberion.tiled.camera.TiledMapCamera;
import net.kiberion.tiled.managers.MapObjectManager;
import net.kiberion.tiled.model.TiledMapInfo;
import net.kiberion.tiled.overlays.TiledMapOverlay;
import net.kiberion.tiled.renderers.OrthogonalTiledMapRendererWithObjects;

public abstract class AbstractTiledMapView<T extends AbstractTiledMapModel<?>> extends StateViewBase<T>{
    
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private CommonViewInfoRegistry viewInfoRegistry;
    
	//@Autowired
	//private ShaderRegistry shaderRegistry;
	
	protected OrthogonalTiledMapRendererWithObjects renderer;
	
	@Getter
    private TiledMapCamera camera;
	
    private TiledMapInfo mapInfo;
    private List<TiledMapOverlay> overlays;
	
    @Setter
    @Getter
    @NonNull
    @Autowired
    //Something like 
    //    binder.bind(MapObjectManager.class).to(OrthographicMapObjectManager.class);
    //should be included in injection binding class
    private MapObjectManager mapObjectManager;
    
    protected Map<MetadataHolderAspect, TextureMapObject> textureMapObjectsMap = new HashMap<>();
    
    
    public AbstractTiledMapView() {
        overlays = new ArrayList<>();
//        occlusionFbo = new FrameBuffer(Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    public void setMap(TiledMapInfo mapInfo) {
    	Objects.requireNonNull(mapInfo);
        this.mapInfo = mapInfo;
        instantiateRenderer();
        camera = new TiledMapCamera(mapInfo.getMap());
        camera.setZoom(1f);
        
        for (TiledMapOverlay overlay : overlays) {
        	overlay.setMap(mapInfo);
        }
        
        this.mapObjectManager.setMapInfo(mapInfo);
    }

    public TiledMap getMap() {
        return mapInfo.getMap();
    }

    protected void instantiateRenderer() {
    	Validate.notNull(renderer);
    	//Validate.notNull(shaderRegistry);
    	//renderer.setShaderRegistry(shaderRegistry);
    }
    
    public void addOverlay(TiledMapOverlay overlay) {
        overlays.add(overlay);
    }

    public void clearOverlays() {
        overlays.clear();
    }

    public void draw() {
        
        //log.info("Camera coords: "+ camera.getOrthoCam().position.x+"/"+ camera.getOrthoCam().position.y);
        
        renderer.setView(camera.getOrthoCam());        
        renderer.render();

        // render overlays
        for (TiledMapOverlay overlay : overlays) {
            overlay.draw(camera.getOrthoCam());
        }
    }

    // Tiled "Object layer" named "objects" should be created for this to work
    public void addMapObject(MetadataHolderAspect entityModel, PositionAspect position, String imageCode) {
        addMapObject(entityModel, position, UiManager.instance().getImage(imageCode));
    }
    
    public void removeMapObject (MetadataHolderAspect entityModel) {
    	mapObjectManager.removeMapObject(entityModel);
    }

    // Tiled "Object layer" named "objects" should be created for this to work
    /**
     * 
     * @param entityModel
     * @param position Model position
     * @param image
     */
    public TextureMapObject addMapObject(MetadataHolderAspect entityModel, PositionAspect position, TextureRegion image) {
    	return mapObjectManager.addMapObject(entityModel, position, image);
    }
    
    public Position getPositionForModelEntity (MetadataHolderAspect entityModel, PositionAspect position) {
        return position;
    }

    public void updateTextureMapObjectPosition(MetadataHolderAspect entityModel) {
    	updateTextureMapObjectPosition (entityModel, ((PositionHolderAspect)entityModel).getPositionAspect());
    }
    
    public void updateTextureMapObjectPosition(MetadataHolderAspect entityModel, PositionAspect position) {
    	mapObjectManager.updateTextureMapObjectPosition(entityModel, position);
        //log.debug(String.format("Camera position: %.2f/%.2f", getCamera().getOrthoCam().position.x, getCamera().getOrthoCam().position.y));
    }
    
    public TiledMapInfo getMapInfo () {
    	return mapInfo;
    }

    public void placeCreatures() {
        for (MetadataHolderAspect entity : this.getModel().getCreatures()) {
            CreatureViewInfo viewInfo = viewInfoRegistry.getFullCreatureViewInfoList().get(entity.getMetadata().getId());
            Objects.requireNonNull(viewInfo);
            TextureMapObject tmo = this.addMapObject(
                    entity,
                    ((PositionHolderAspect) entity).getPositionAspect(),
                    viewInfo.image);
        }
    }
    
    public void centerCameraOnPlayer() {
        //getCamera().centerIsometrically(getModel().getPlayer().getPositionAspect(), getModel().getMapInfo());
        throw new UnsupportedOperationException("unsupported");
    } 

}
