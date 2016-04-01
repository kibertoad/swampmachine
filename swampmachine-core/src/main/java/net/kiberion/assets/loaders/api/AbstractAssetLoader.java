package net.kiberion.assets.loaders.api;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.assets.GameConfig;

public abstract class AbstractAssetLoader implements AssetLoader{

    @Setter
    @Getter
    @Autowired
    private GameConfig config;
    
}
