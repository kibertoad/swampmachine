package net.kiberion.swampmachine.assets.loaders.api;

public interface AsyncLoader extends Loader{

    public void startAsyncLoading();
    public void finishAsyncLoading();

    @Override
    public default void load() {
        startAsyncLoading();
        finishAsyncLoading();
    }
    
}
