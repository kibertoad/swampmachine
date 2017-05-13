package net.kiberion.swampmachine.loaders;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.kiberion.swampmachine.utils.SetUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import lombok.Setter;
import net.kiberion.swampmachine.assets.loaders.api.AsyncLoader;
import net.kiberion.swampmachine.assets.loaders.api.Loader;
import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;
import net.kiberion.swampmachine.assets.loaders.util.LoaderSpringExtractor;
import net.kiberion.swampmachine.utils.ImmutableRegistryPreparer;

public class LoaderHelper implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LogManager.getLogger();

    private final Set<SyncLoader> syncAssetLoaders = new TreeSet<>();
    private final Set<AsyncLoader> asyncAssetLoaders = new TreeSet<>();

    @Setter
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Loader> assetLoaders = LoaderSpringExtractor
                .extractSortedStartupAssetLoadersFromContext(applicationContext);
        for (Loader assetLoader : assetLoaders) {
            if (assetLoader instanceof AsyncLoader) {
                SetUtils.validateNoDuplicatePriority(asyncAssetLoaders, assetLoader);
                asyncAssetLoaders.add((AsyncLoader) assetLoader);
            } else if (assetLoader instanceof SyncLoader) {
                SetUtils.validateNoDuplicatePriority(syncAssetLoaders, assetLoader);
                syncAssetLoaders.add((SyncLoader) assetLoader);
            } else {
                throw new IllegalStateException("Unsupported subclass of an AssetLoader: " + assetLoader.getClass());
            }
        }
    }

    public void startLoading() {
        log.info("Start loading from Sync asset loaders.");
        log.info("List of sync loaders:.");
        for (SyncLoader loader : syncAssetLoaders) {
            log.info("  " + loader.toString());
        }

        for (SyncLoader loader : syncAssetLoaders) {
            loader.load();
        }
        log.info("Done loading from Sync asset loaders.");

        log.info("Start queueing loading from Async asset loaders.");
        for (AsyncLoader loader : asyncAssetLoaders) {
            loader.startAsyncLoading();
        }
        log.info("Done queueing loading from Async asset loaders.");

    }

    public void finishLoading() {
        log.info("Start finishing loading from Async asset loaders.");
        for (AsyncLoader loader : asyncAssetLoaders) {
            loader.finishAsyncLoading();
        }
        log.info("Done finishing loading from Async asset loaders.");

        ImmutableRegistryPreparer.invoke(applicationContext);
    }

    protected Set<AsyncLoader> getAsyncAssetLoaders() {
        return asyncAssetLoaders;
    }

    protected Set<SyncLoader> getSyncAssetLoaders() {
        return syncAssetLoaders;
    }
}
