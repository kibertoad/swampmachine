package net.kiberion.swampmachine.assets.loaders.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.assets.loaders.api.AssetLoader;
import net.kiberion.swampmachine.assets.util.LoadBeforeStartup;
import net.kiberion.swampmachine.assets.util.LoadOnStartup;

/**
 * Class retrieves all beans that implement {@link AssetLoader} interface and have either {@link LoadOnStartup} or {@link LoadBeforeStartup} annotation
 * and returns them as a list, sorted by loader priority (lower priority loaders go first).
 * Methods throw {@link IllegalStateException} if there is a bean with specified annotation that is not an {@link AssetLoader}
 * 
 * @author kibertoad
 *
 */
public class AssetLoaderSpringExtractor {

    private AssetLoaderSpringExtractor() {
    }

    /**
     * 
     * @param context
     * @return Sorted TreeSet
     */
    public static List<AssetLoader> extractSortedStartupAssetLoadersFromContext(ApplicationContext context) {
        return extractSortedStartupAssetLoadersFromContext (context, LoadOnStartup.class);
    }
    
    public static List<AssetLoader> extractSortedPreStartupAssetLoadersFromContext(ApplicationContext context) {
        return extractSortedStartupAssetLoadersFromContext (context, LoadBeforeStartup.class);
    }
    
    
    private static List<AssetLoader> extractSortedStartupAssetLoadersFromContext(ApplicationContext context, Class<? extends Annotation> annotationClass) {
        List<AssetLoader> result = new ArrayList<>();
        Collection<Object> assetLoaderBeans = context.getBeansWithAnnotation(annotationClass).values();

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
