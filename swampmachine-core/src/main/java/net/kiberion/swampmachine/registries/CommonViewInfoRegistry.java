package net.kiberion.swampmachine.registries;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.assets.viewinfo.AnimationViewInfo;
import net.kiberion.swampmachine.assets.viewinfo.CreatureViewInfo;
import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;

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
