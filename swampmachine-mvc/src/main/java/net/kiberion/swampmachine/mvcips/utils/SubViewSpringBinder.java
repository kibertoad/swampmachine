package net.kiberion.swampmachine.mvcips.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.scenes.scene2d.Stage;

import net.kiberion.swampmachine.gui.view.SubView;
import net.kiberion.swampmachine.gui.view.StateView;

public class SubViewSpringBinder {

    public static void bindSubViewsFromContext(ApplicationContext context) {
        Map<String, Object> stateBeans = context.getBeansWithAnnotation(SubView.class);

        for (Entry<String, Object> entry : stateBeans.entrySet()) {
            Validate.isTrue(entry.getValue() instanceof StateView,
                    entry.getKey() + " does not implement StateView interface.");

            SubView subViewAnnotation = entry.getValue().getClass().getAnnotation(SubView.class);

            for (Class<? extends StateView> parentId : subViewAnnotation.parentViews()) {
                StateView parentView = context.getBean(parentId);
                StateView childView = (StateView) entry.getValue();
                parentView.addSubView(childView);

                if (childView.getMainStage() == null) {
                    if (subViewAnnotation.usesOverlayStage()) {
                        childView.setMainStage(new Stage());
                    } else {
                        childView.setMainStage(parentView.getMainStage());
                    }
                }
            }
        }

    }
}
