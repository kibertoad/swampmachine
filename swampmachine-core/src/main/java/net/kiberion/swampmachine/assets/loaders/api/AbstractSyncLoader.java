package net.kiberion.swampmachine.assets.loaders.api;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;

public abstract class AbstractSyncLoader implements SyncLoader{

    @Setter
    @Getter
    @Autowired
    private GameConfig config;
    
}
