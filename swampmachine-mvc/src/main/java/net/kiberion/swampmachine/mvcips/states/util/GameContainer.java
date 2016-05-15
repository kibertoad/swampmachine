package net.kiberion.swampmachine.mvcips.states.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.assets.registries.LoaderRegistry;
import net.kiberion.swampmachine.loaders.CreatureLoader;
import net.kiberion.swampmachine.loaders.CommonViewInfoLoader;
import net.kiberion.swampmachine.registries.CommonModelInfoRegistry;
import net.kiberion.swampmachine.registries.CommonViewInfoRegistry;

/**
 * Container class for game stats and global registries (for game assets, map, model info etc)
 * @author kibertoad
 *
 */

@Component
public class GameContainer {

    @Autowired
    @Getter
    private StateRegistry stateRegistry;
    
    @Autowired
    @Getter
    private LoaderRegistry loaderRegistry;
    
    @Autowired
    @Getter
    private CommonModelInfoRegistry commonModelInfoRegistry;

    @Autowired
    @Getter
    private CommonViewInfoRegistry commonViewInfoRegistry;
    
    @Autowired
    @Getter
    private CommonViewInfoLoader viewInfoLoader;
    
    @Autowired
    @Getter
    private CreatureLoader modelInfoLoader;
    

}
