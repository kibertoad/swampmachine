package net.kiberion.swampmachine.blueprint.common.loaders;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;

import net.kiberion.swampmachine.assets.AssetProvider;
import net.kiberion.swampmachine.assets.loaders.api.AbstractLoader;
import net.kiberion.swampmachine.assets.loaders.api.POJOLoader;
import net.kiberion.swampmachine.assets.loaders.impl.EntityViewInfoLoader;
import net.kiberion.swampmachine.assets.loaders.impl.GameViewInfoLoader;
import net.kiberion.swampmachine.assets.readers.AbstractFileReader;
import net.kiberion.swampmachine.assets.readers.GDXFileReader;
import net.kiberion.swampmachine.assets.readers.SimpleFileReader;
import net.kiberion.swampmachine.assets.viewinfo.AnimationViewInfo;
import net.kiberion.swampmachine.assets.viewinfo.CreatureViewInfo;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;
import net.kiberion.swampmachine.entities.modelinfo.CreatureModelInfo;

/**
 * @author kibertoad
 */
public class GameViewInfoProvider {

    private static final Logger log = LogManager.getLogger();
    
    public static boolean isInitted;
    private static GameViewInfoProvider _instance;

    public GameViewInfoLoader customItemViewLoader;

    public Map<String, CreatureViewInfo> fullCreatureViewInfoList;
    public Map<String, ViewInfo> buildingViewInfoList;
    public Map<String, ViewInfo> gameViewInfoList;
    public Map<String, AnimationViewInfo> animationViewInfoList;
    public Map<String, ViewInfo> itemViewInfoList;
    
    //public Map<String, DirectionalAnimationList> creatureMovementAnimation;
    
    private AbstractFileReader fileReader;

    
    public Path getPathToAssets (){
        return AssetProvider.getPathToAssets();        
    }
    
    public GameViewInfoProvider() {
        if (Gdx.app != null) {
            fileReader = new GDXFileReader(getPathToAssets());
        } else {
            fileReader = new SimpleFileReader(getPathToAssets());
        }
    }
    
    public void loadAnimationViewInfo() {
        //if (Gdx.files.internal(GameModelDataProvider.getPathToAssets()+"animation/").exists()) {
        if (fileReader.fileExists(getPathToAssets().resolve("animation"))) {
            log.info("Start loading animation");
            AbstractLoader<AnimationViewInfo> loader = new POJOLoader<AnimationViewInfo>(getPathToAssets().resolve("animation/"), AnimationViewInfo.class, "view");
            
            try {
                animationViewInfoList = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Gdx.app.log("debug", "Done loading game animation");
    }

    public void loadGameViewInfo() {
        log.info("Try loading gameimages from: "+getPathToAssets().resolve("view-game").toString());
        
        if (fileReader.fileExists(getPathToAssets().resolve("view-game/"))) {
            log.info("Start loading gameimages from: "+getPathToAssets().resolve("view-game").toString());
            GameViewInfoLoader loader = new GameViewInfoLoader(getPathToAssets().resolve("view-game").toString()+"/*");
            gameViewInfoList = loader.load();
        }

        log.info("Done loading gameimages");
    }

    public void loadCreatureViewInfoFromModel(Map<String, CreatureModelInfo> fullCreatureList) {
        if (fileReader.fileExists(getPathToAssets().resolve("model-creature/"))) {
            log.info("debug", "Start loading creature images");
            EntityViewInfoLoader loader = new EntityViewInfoLoader(getPathToAssets().resolve("model-creature/").toString()+"*", fullCreatureList, "creatures");
            this.fullCreatureViewInfoList = loader.load();

            /*
            try {
                this.creatureMovementAnimation = new POJOLoader<DirectionalAnimationList>(getPathToAssets().resolve("view-creature/"), DirectionalAnimationList.class, "view").setWildcardFileExtension("creatures").loadToMap();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            
            log.info("Done loading creature images");
        }
    }
    
    /*
    public void loadBuildingViewInfoFromModel(Map<String, BuildingModelInfo> buildingList) throws IOException {
        if (fileReader.fileExists(getPathToAssets().resolve("model-building/"))) {
            EntityViewInfoLoader loader = new EntityViewInfoLoader(getPathToAssets().resolve("model-building/").toString()+"*", buildingList, "buildings");
            this.buildingViewInfoList = new HashMap<String, ViewInfo>(loader.loadList());

            log.info("Done loading building images");
        }
    }
    */
    

/*
    public void loadItemViewInfoFromModel(Map<String, ItemInfo> fullItemList, boolean mandatoryImages) {

        if (fileReader.fileExists(getPathToAssets().resolve("model-items/"))) {
            Gdx.app.log("debug", "Start loading item images");

            GameViewInfoLoader loader;
            if (customItemViewLoader == null) {
                loader = new GameViewInfoLoader(AssetProvider.getPathToAssets()+"model-items/*", fullItemList);
                loader.setWildcardFileExtension ("items");
            } else {
                loader = customItemViewLoader;
            }

            loader.imageIsMandatory = mandatoryImages;

            this.itemViewInfoList = loader.load();

            log.info("Done loading item images");
        }
    }
    */

    public void init () throws IOException{
        loadGameViewInfo();
        loadAnimationViewInfo();
        loadCreatureViewInfoFromModel(AssetProvider.instance().getCreatures());
        //loadBuildingViewInfoFromModel(AssetProvider.instance().getBuildings());
        //loadItemViewInfoFromModel(AssetProvider.instance().getItems(), false);
        isInitted = true;
    }

    public static GameViewInfoProvider instance() {
        if (_instance == null) {
            _instance = new GameViewInfoProvider();
        }
        if (!_instance.isInitted) {
            try {
				_instance.init();
			} catch (IOException e) {
				log.error (e, e);
				throw new IllegalStateException ("Uh-oh.");
			}
        }
        return _instance;
    }

    public static GameViewInfoProvider instanceNoInit() {
        if (_instance == null) {
            _instance = new GameViewInfoProvider();
        }
        return _instance;
    }

    public static void reset() {
        _instance = null;
        isInitted = false;
    }
    
    

}
