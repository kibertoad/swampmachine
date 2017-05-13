/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.assets.loaders.impl;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.loaders.impl.EntityViewInfoLoader.ImageModelEntityDescriptor;
import net.kiberion.swampmachine.entities.common.impl.AbstractModelEntityDescriptor;


/**
 * @author kibertoad
 */
public class EntityViewInfoLoader extends CommonYamlLoader<ImageModelEntityDescriptor> {

    private static final String IMAGE_ATTRIBUTE = "image";
    
    @Setter
    public boolean imageIsMandatory = true;

    public EntityViewInfoLoader(String fromPath, String wildcard) {
        super(fromPath);

        setSupportedFileExtensions(wildcard);
    }

    @Override
    protected ImageModelEntityDescriptor initNewEntity() {
        return new ImageModelEntityDescriptor();
    }
    
    @Override
    protected void parseYaml(Object sourceYamlObject, ImageModelEntityDescriptor targetObject) {
        super.parseYaml(sourceYamlObject, targetObject);

        String imageId = getYamlLoader().getString(IMAGE_ATTRIBUTE);
        if ((imageId == null) && (imageIsMandatory)) {
            throw new IllegalArgumentException ("Image not set for entity: "+targetObject.getId());
        }
        targetObject.setImageId(imageId);

        results.put(targetObject.getMetadata().getId(), targetObject);
    }
    
    
    
    public static class ImageModelEntityDescriptor extends AbstractModelEntityDescriptor {
        @Getter
        @Setter
        private String imageId;
        
    }
}
