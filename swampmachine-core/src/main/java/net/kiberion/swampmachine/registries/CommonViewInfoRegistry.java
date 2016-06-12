package net.kiberion.swampmachine.registries;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.assets.viewinfo.AnimationViewInfo;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;

@ImmutableRegistry
public class CommonViewInfoRegistry {

    @Getter
    private Map<String, ViewInfo> gameViewInfoList = new HashMap<>();

    @Getter
    private Map<String, AnimationViewInfo> animationViewInfoList = new HashMap<>();

    /**
     * Maps entity ID to image ID
     */
    @Getter
    private Map<String, String> entityViewMap = new HashMap<>();

    public String getImageIdForEntity(String entityId) {
        String imageId = entityViewMap.get(entityId);

        // if there is no explicit mapping for entity, assume there is an image
        // which filename is directly mapped to entity id
        return imageId != null ? imageId : entityId;
    }
}
