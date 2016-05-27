package net.kiberion.persistence;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;

/**
 * Class retrieves all beans that implement {@link Persistor} interface and have either {@link PersistOnStartup} annotation
 * and returns them as a list, sorted by persistor priority (lower priority persistors go first).
 * Methods throw {@link IllegalStateException} if there is a bean with specified annotation that is not an {@link  Persistor}
 * 
 * @author kibertoad
 *
 */
public class PersistorSpringExtractor {

    private PersistorSpringExtractor() {
    }

    /**
     * 
     * @param context
     * @return Sorted TreeSet
     */
    public static List<Persistor> extractSortedStartupAssetLoadersFromContext(ApplicationContext context) {
        return extractSortedStartupAssetLoadersFromContext (context, PersistOnStartup.class);
    }
    
    private static List<Persistor> extractSortedStartupAssetLoadersFromContext(ApplicationContext context, Class<? extends Annotation> annotationClass) {
        List<Persistor> result = new ArrayList<>();
        Collection<Object> persistorBeans = context.getBeansWithAnnotation(annotationClass).values();

        for (Object bean : persistorBeans) {
            if (!(bean instanceof Persistor)) {
                throw new IllegalStateException(
                        bean.getClass() + " does not implement Persistor interface but has PersistOnStartup annotation.");
            }

            result.add((Persistor) bean);
        }

        Collections.sort(result);
        return result;
    }

}
