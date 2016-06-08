package net.kiberion.swampmachine.mvcips.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;

import net.kiberion.swampmachine.gui.view.StateView;
import net.kiberion.swampmachine.mvcips.states.annotations.SubView;

public class SubViewSpringBinder {

    public static void bindSubViewsFromContext(ApplicationContext context) {
        Map<String, Object> stateBeans = context.getBeansWithAnnotation(SubView.class);

        for (Entry<String, Object> entry : stateBeans.entrySet()) {
            Validate.isTrue(entry.getValue() instanceof StateView,
                    entry.getKey() + " does not implement StateView interface.");

            SubView subViewAnnotation = entry.getValue().getClass().getAnnotation(SubView.class);

            StateView parentView = context.getBean(subViewAnnotation.parentView());
            parentView.addSubView((StateView) entry.getValue());
        }
    }
}
