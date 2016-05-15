package net.kiberion.swampmachine.assets.loaders.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.kiberion.swampmachine.assets.AssetProvider;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 * @author: kibertoad
 */
public class GameViewInfoLoader extends CommonYamlLoader<ViewInfo> {

    private static final Logger log = LogManager.getLogger();

    public static String pathToImages = "img/";
    public Map<String, ? extends CommonModelEntityDescriptor> listOfModelInfo;

    public String imageName;
    public boolean imageIsMandatory;

    public GameViewInfoLoader(String fromPath, Map<String, ? extends CommonModelEntityDescriptor> setListofModelInfo,
            String... wildcards) {
        super(fromPath);

        if (wildcards.length > 0) {
            setWildcardFileExtension(wildcards);
        } else {
            setWildcardFileExtension("view");
        }
        listOfModelInfo = setListofModelInfo;
    }

    public GameViewInfoLoader(String fromPath, String... wildcards) {
        this(fromPath, (Map<String, ? extends CommonModelEntityDescriptor>) null, wildcards);
    }

    @Override
    protected ViewInfo initNewEntity() {
        return new ViewInfo();
    }

    @Override
    public void parseYaml(Object sourceYamlObject, ViewInfo targetObject) {
        super.parseYaml(sourceYamlObject, targetObject);

        TextureRegion image = getYamlLoader().getImage("image");
        // image.getTexture().setFilter(Texture.TextureFilter.Linear,
        // Texture.TextureFilter.Linear);

        if (listOfModelInfo != null) {
            targetObject.getTags().addAll(listOfModelInfo.get(targetObject.getId()).getTags());
            targetObject.setGroup(listOfModelInfo.get(targetObject.getId()).getGroup());

            // Gdx.app.log("debug", "Bound "+entityCode+" to model
            // "+listOfModelInfo.getByCode(entityCode).code);
            // Gdx.app.log("debug", "Bound ID "+viewInfo.ID+" to model ID
            // "+listOfModelInfo.getByCode(entityCode).ID);
        }

        imageName = getYamlLoader().getString("image");

        if ((imageName == null) || (!(imageName).equals("BLANK"))) {

            targetObject.setImage(image);

            if (image != null) {
                targetObject.setDrawableImage(new TextureRegionDrawable(image));
                targetObject.setPicture(new Pixmap(Gdx.files.getFileHandle(
                        AssetProvider.getPathToAssets().resolve(pathToImages).resolve(imageName + ".png").toString(),
                        Files.FileType.Internal)));
            } else {
                if (imageIsMandatory) {
                    log.error("error", "No image set for: " + targetObject.getId());
                }
            }
        }

        results.put(targetObject.getId(), targetObject);
    }
}