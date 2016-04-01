package net.kiberion.assets.loaders.api;

public interface AssetLoader extends Comparable<AssetLoader>{

	public void load();

	default public int getPriority () {
	    return 100;
	}
	
	@Override
	default int compareTo(AssetLoader o) {
	    if (o == null) {
	        return 1;
	    }
	    return Integer.compare(getPriority(), o.getPriority());
	}
	
}
