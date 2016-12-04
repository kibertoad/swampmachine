package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.assets.viewinfo.ViewInfo;
import net.kiberion.swampmachine.registries.ImageRegistry;
import net.kiberion.swampmachine.utils.SetUtils;

public class ViewInfoTransformer extends AbstractTransformer<String, ViewInfo> {

    public static final Set<String> TRANSFORMED_PARAMETER = SetUtils.buildSet("selectedIcon", "unselectedIcon");

    @Autowired
    private ImageRegistry imageRegistry;

    @Override
    public ViewInfo transformSingle(String parameter, Map<String, Object> context) {
        ViewInfo viewInfo = imageRegistry.getImages().get(parameter);
        Validate.notNull(viewInfo, "Unknown viewInfo: "+parameter);
        return viewInfo;
    }

    @Override
    public Collection<String> getParameterNames() {
        return TRANSFORMED_PARAMETER;
    }

}
