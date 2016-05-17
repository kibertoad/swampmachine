/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.assets.loaders.impl;

import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import lombok.Setter;
import net.kiberion.swampmachine.assets.viewinfo.EntityViewInfo;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;


/**
 * @author kibertoad
 */
public class EntityViewInfoLoader extends CommonYamlLoader<EntityViewInfo> {

    private static final Logger log = LogManager.getLogger();
    
    public Map<String, ? extends CommonModelEntityDescriptor> entities;
    
    @Setter
    public boolean imageIsMandatory = true;

    public EntityViewInfoLoader(String fromPath, Map<String, ? extends CommonModelEntityDescriptor> setCreatures, String wildcard) {
        super(fromPath);

        setSupportedFileExtensions(wildcard);
        entities = setCreatures;
    }

    @Override
    protected EntityViewInfo initNewEntity() {
        return new EntityViewInfo();
    }
    
    @Override
    protected void parseYaml(Object sourceYamlObject, EntityViewInfo targetObject) {
        super.parseYaml(sourceYamlObject, targetObject);

        TextureRegion image = getYamlLoader().getImage("image");

        if ((entities == null) || (entities.isEmpty())) {
            log.error("No creatures received.");
        }

        Objects.requireNonNull(entities);

        if ((image == null) && (imageIsMandatory)) {
            Objects.requireNonNull(image, "Unknown image: " + getYamlLoader().getString("image"));
        }

        if (image != null) {
            targetObject.setDrawableImage (new TextureRegionDrawable(image));
            targetObject.setImage (image);
        }

        results.put(targetObject.getMetadata().getId(), targetObject);
    }
}
