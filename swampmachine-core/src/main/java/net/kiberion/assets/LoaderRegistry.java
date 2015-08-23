package net.kiberion.assets;

import java.util.ArrayList;
import java.util.List;

import net.kiberion.assets.loaders.AssetLoader;

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
