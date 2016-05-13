package net.kiberion.swampmachine.assets.loaders.api;

public interface AsyncAssetLoader extends AssetLoader{

    public void startAsyncLoading();
    public void finishAsyncLoading();

    @Override
    default void load() {
        startAsyncLoading();
        finishAsyncLoading();
    }
    
}
