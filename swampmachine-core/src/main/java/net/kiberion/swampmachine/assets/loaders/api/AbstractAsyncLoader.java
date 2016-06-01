package net.kiberion.swampmachine.assets.loaders.api;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.GameConfig;

public abstract class AbstractAsyncLoader implements AsyncLoader{

    @Setter
    @Getter
    @Autowired
    private GameConfig config;
    
}
