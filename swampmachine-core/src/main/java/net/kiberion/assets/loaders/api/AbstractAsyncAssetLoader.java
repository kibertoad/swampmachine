package net.kiberion.assets.loaders.api;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.assets.GameConfig;

public abstract class AbstractAsyncAssetLoader implements AsyncAssetLoader{

    @Setter
    @Getter
    @Autowired
    private GameConfig config;
    
}
