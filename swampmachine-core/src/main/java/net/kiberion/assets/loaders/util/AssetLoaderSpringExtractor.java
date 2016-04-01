package net.kiberion.assets.loaders.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;

import net.kiberion.assets.loaders.api.AssetLoader;
import net.kiberion.assets.util.LoadOnStartup;

public class AssetLoaderSpringExtractor {

    private AssetLoaderSpringExtractor() {
    }

    /**
     * 
     * @param context
     * @return Sorted TreeSet
     */
    public static List<AssetLoader> extractSortedStartupAssetLoadersFromContext(ApplicationContext context) {
        List<AssetLoader> result = new ArrayList<>();
        Collection<Object> assetLoaderBeans = context.getBeansWithAnnotation(LoadOnStartup.class).values();

        for (Object bean : assetLoaderBeans) {
            if (!(bean instanceof AssetLoader)) {
                throw new IllegalStateException(
                        bean.getClass() + " does not implement AssetLoader interface but has LoadOnStartup annotation.");
            }

            result.add((AssetLoader) bean);
        }

        Collections.sort(result);
        return result;
    }

}
