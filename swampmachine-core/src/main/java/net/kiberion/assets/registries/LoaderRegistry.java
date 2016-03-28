package net.kiberion.assets.registries;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.google.inject.Singleton;

import lombok.Getter;
import net.kiberion.assets.loaders.AssetLoader;

@Singleton
@Component
public class LoaderRegistry{

    @Getter
	private Set <AssetLoader> registeredLoaders = new TreeSet<>();
	
	public void load() {
		for (AssetLoader loader : registeredLoaders) {
			loader.load();
		}
	}
	
	public void registerLoader (AssetLoader loader) {
		registeredLoaders.add(loader);
	}

    public void registerLoaders(List<AssetLoader> loaders) {
        if (loaders == null) {
            return;
        }
        
        for (AssetLoader loader : loaders) {
            registerLoader(loader);
        }
    }
	
}
