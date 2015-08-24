package net.kiberion.assets.registries;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Singleton;

import net.kiberion.assets.loaders.AssetLoader;

@Singleton
public class LoaderRegistry{

	private List <AssetLoader> registeredLoaders = new ArrayList<>();
	
	public void load() {
		for (AssetLoader loader : registeredLoaders) {
			loader.load();
		}
	}
	
	public void registerLoader (AssetLoader loader) {
		registeredLoaders.add(loader);
	}
}
