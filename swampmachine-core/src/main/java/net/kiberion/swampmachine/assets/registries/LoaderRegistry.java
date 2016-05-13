package net.kiberion.swampmachine.assets.registries;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;

@Component
public class LoaderRegistry{

    @Getter
	private Set <SyncAssetLoader> registeredLoaders = new TreeSet<>();
	
	public void load() {
		for (SyncAssetLoader loader : registeredLoaders) {
			loader.load();
		}
	}
	
	public void registerLoader (SyncAssetLoader loader) {
		registeredLoaders.add(loader);
	}

    public void registerLoaders(List<SyncAssetLoader> loaders) {
        if (loaders == null) {
            return;
        }
        
        for (SyncAssetLoader loader : loaders) {
            registerLoader(loader);
        }
    }
	
}
