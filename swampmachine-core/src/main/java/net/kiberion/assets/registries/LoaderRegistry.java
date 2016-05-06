package net.kiberion.assets.registries;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.google.inject.Singleton;

import lombok.Getter;
import net.kiberion.assets.loaders.api.SyncAssetLoader;

@Singleton
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
