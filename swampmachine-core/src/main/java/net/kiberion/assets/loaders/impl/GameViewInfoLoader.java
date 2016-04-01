package net.kiberion.assets.loaders.impl;

import java.util.Map;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.kiberion.assets.AssetProvider;
import net.kiberion.assets.viewinfo.ViewInfo;
import net.kiberion.entities.common.impl.DataNode;

/**
 * @author: kibertoad
 */
public class GameViewInfoLoader extends DataNodeLoader<ViewInfo>{

    public static String pathToImages = "img/";
    public Map<String, ? extends DataNode> listOfModelInfo;

    public String imageName;
    public boolean imageIsMandatory;

    public GameViewInfoLoader(String fromPath, Map<String, ? extends DataNode> setListofModelInfo, String... wildcards)
    {
        super(fromPath);

        if (wildcards.length > 0) {
            setWildcardFileExtension(wildcards);
        } else {
            setWildcardFileExtension("view");
        }
        listOfModelInfo = setListofModelInfo;
    }

    public GameViewInfoLoader(String fromPath, String... wildcards) {
        this(fromPath, (Map<String, ? extends DataNode>)null, wildcards);
    }


    @Override
    public void parseYaml(Object o) {
        super.parseYaml(o);

        ViewInfo viewInfo = new ViewInfo();

        TextureRegion image = ya.getImage("image");
        //image.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        if (listOfModelInfo != null) {
            viewInfo.tags.addAll(listOfModelInfo.get(entityCode).getTags());
            viewInfo.group = listOfModelInfo.get(entityCode).getGroup();

            //Gdx.app.log("debug", "Bound "+entityCode+" to model "+listOfModelInfo.getByCode(entityCode).code);
            //Gdx.app.log("debug", "Bound ID "+viewInfo.ID+" to model ID "+listOfModelInfo.getByCode(entityCode).ID);
        }

        viewInfo.setId (entityCode);

        imageName = ya.getString("image");

        if ((imageName == null) || (!(imageName).equals("BLANK"))) {

            //Gdx.app.log("debug", "Try to add image: " + imageName+ ", ID: "+viewInfo.ID);

            viewInfo.image = image;

            if (image != null) {

            viewInfo.drawableImage = new TextureRegionDrawable(image);
            viewInfo.picture = new Pixmap(Gdx.files.getFileHandle(AssetProvider.getPathToAssets().resolve(pathToImages).resolve(imageName+".png").toString(), Files.FileType.Internal));
            } else {
                if (imageIsMandatory) {
                    Gdx.app.error("error", "No image set for: "+entityCode);
                }
            }

           // Gdx.app.log("debug", "Added image: " + imageName+ ", ID: "+viewInfo.ID);
        }


        results.put(viewInfo.getId(), viewInfo);
    }
}