/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.assets.loaders;

import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.kiberion.assets.viewinfo.CreatureViewInfo;
import net.kiberion.entities.common.impl.DataNode;


/**
 * @author kibertoad
 */
public class EntityViewInfoLoader extends DataNodeLoader<CreatureViewInfo> {

    private static final Logger log = LogManager.getLogger();
    
    public Map<String, ? extends DataNode> entities;
    public boolean imageIsMandatory = true;

    public EntityViewInfoLoader(String fromPath, Map<String, ? extends DataNode> setCreatures, String wildcard) {
        super(fromPath);

        setWildcardFileExtension(wildcard);

        entities = setCreatures;
    }

    @Override
    public void parseYaml(Object o) {
        super.parseYaml(o);

        CreatureViewInfo creature = new CreatureViewInfo();

        TextureRegion image = ya.getImage("image");

        if ((entities == null) || (entities.isEmpty())) {
            log.error("No creatures received.");
        }

        Objects.requireNonNull(entities);

        if ((image == null) && (imageIsMandatory)) {
            Objects.requireNonNull(image, "Unknown image: " + ya.getString("image"));
        }

        creature.setId (entities.get(entityCode).getId());

        if (image != null) {
            creature.drawableImage = new TextureRegionDrawable(image);
            creature.image = image;
        }

        //Objects.requireNonNull(creature.drawableImage);

        //Log.info("Added image: " + ya.getString("image"));

        results.put(creature.getId(), creature);
    }
}
