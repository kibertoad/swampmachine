package net.kiberion.swampmachine.assets.loaders.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.assets.loaders.api.Loader;
import net.kiberion.swampmachine.annotations.LoadBeforeStartup;
import net.kiberion.swampmachine.annotations.LoadOnStartup;

/**
 * Class retrieves all beans that implement {@link Loader} interface and have either {@link LoadOnStartup} or {@link LoadBeforeStartup} annotation
 * and returns them as a list, sorted by loader priority (lower priority loaders go first).
 * Methods throw {@link IllegalStateException} if there is a bean with specified annotation that is not an {@link Loader}
 * 
 * @author kibertoad
 *
 */
public class LoaderSpringExtractor {

    private LoaderSpringExtractor() {
    }

    /**
     * 
     * @param context
     * @return Sorted TreeSet
     */
    public static List<Loader> extractSortedStartupAssetLoadersFromContext(ApplicationContext context) {
        return extractSortedStartupAssetLoadersFromContext (context, LoadOnStartup.class);
    }
    
    public static List<Loader> extractSortedPreStartupAssetLoadersFromContext(ApplicationContext context) {
        return extractSortedStartupAssetLoadersFromContext (context, LoadBeforeStartup.class);
    }
    
    
    private static List<Loader> extractSortedStartupAssetLoadersFromContext(ApplicationContext context, Class<? extends Annotation> annotationClass) {
        List<Loader> result = new ArrayList<>();
        Collection<Object> assetLoaderBeans = context.getBeansWithAnnotation(annotationClass).values();

        for (Object bean : assetLoaderBeans) {
            if (!(bean instanceof Loader)) {
                throw new IllegalStateException(
                        bean.getClass() + " does not implement AssetLoader interface but has LoadOnStartup annotation.");
            }

            result.add((Loader) bean);
        }

        Collections.sort(result);
        return result;
    }

}
