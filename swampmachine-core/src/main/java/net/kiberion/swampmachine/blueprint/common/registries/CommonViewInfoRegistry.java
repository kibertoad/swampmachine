package net.kiberion.swampmachine.blueprint.common.registries;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.assets.viewinfo.AnimationViewInfo;
import net.kiberion.swampmachine.assets.viewinfo.CreatureViewInfo;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;

@Component
public class CommonViewInfoRegistry {

    @Getter
    private Map<String, CreatureViewInfo> fullCreatureViewInfoList = new HashMap<>();
    
    @Getter
    private Map<String, ViewInfo> buildingViewInfoList = new HashMap<>();
    
    @Getter
    private Map<String, ViewInfo> gameViewInfoList = new HashMap<>();
    
    @Getter
    private Map<String, AnimationViewInfo> animationViewInfoList = new HashMap<>();
    
    @Getter
    private Map<String, ViewInfo> itemViewInfoList = new HashMap<>();
    

}
