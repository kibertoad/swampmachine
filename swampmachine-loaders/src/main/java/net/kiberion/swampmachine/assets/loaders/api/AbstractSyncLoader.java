package net.kiberion.swampmachine.assets.loaders.api;

import net.kiberion.swampmachine.assets.GameConfig;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractSyncLoader implements SyncLoader{

    @Setter
    @Getter
    @Autowired
    private GameConfig config;
    
}
