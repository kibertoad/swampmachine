package net.kiberion.blueprint.common.registries;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;

import lombok.Getter;
import net.kiberion.assets.viewinfo.AnimationViewInfo;
import net.kiberion.assets.viewinfo.CreatureViewInfo;
import net.kiberion.assets.viewinfo.ViewInfo;

@Singleton
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
