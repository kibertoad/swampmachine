package net.kiberion.swampmachine.loaders;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.Setter;
import net.kiberion.swampmachine.assets.loaders.api.AssetLoader;
import net.kiberion.swampmachine.assets.loaders.api.AsyncAssetLoader;
import net.kiberion.swampmachine.assets.loaders.api.SyncAssetLoader;
import net.kiberion.swampmachine.assets.loaders.util.AssetLoaderSpringExtractor;
import net.kiberion.swampmachine.utils.SetUtils;

public class LoaderHelper implements InitializingBean, ApplicationContextAware {

    private static final Logger log = LogManager.getLogger();

    private final Set<SyncAssetLoader> syncAssetLoaders = new TreeSet<>();
    private final Set<AsyncAssetLoader> asyncAssetLoaders = new TreeSet<>();

    @Setter
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<AssetLoader> assetLoaders = AssetLoaderSpringExtractor
                .extractSortedStartupAssetLoadersFromContext(applicationContext);
        for (AssetLoader assetLoader : assetLoaders) {
            if (assetLoader instanceof AsyncAssetLoader) {
                SetUtils.validateNoDuplicatePriority(asyncAssetLoaders, assetLoader);
                asyncAssetLoaders.add((AsyncAssetLoader) assetLoader);
            }
            if (assetLoader instanceof SyncAssetLoader) {
                SetUtils.validateNoDuplicatePriority(syncAssetLoaders, assetLoader);
                syncAssetLoaders.add((SyncAssetLoader) assetLoader);
            }
        }
    }
    
    public void startLoading() {
        log.info("Start loading from Sync asset loaders.");
        for (SyncAssetLoader loader : syncAssetLoaders) {
            loader.load();
        }
        log.info("Done loading from Sync asset loaders.");

        log.info("Start queueing loading from Async asset loaders.");
        for (AsyncAssetLoader loader : asyncAssetLoaders) {
            loader.startAsyncLoading();
        }
        log.info("Done queueing loading from Async asset loaders.");

    }

    public void finishLoading() {
        log.info("Start finishing loading from Async asset loaders.");
        for (AsyncAssetLoader loader : asyncAssetLoaders) {
            loader.finishAsyncLoading();
        }
        log.info("Done finishing loading from Async asset loaders.");
    }

    protected Set<AsyncAssetLoader> getAsyncAssetLoaders() {
        return asyncAssetLoaders;
    }

    protected Set<SyncAssetLoader> getSyncAssetLoaders() {
        return syncAssetLoaders;
    }
}
