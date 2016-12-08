package net.kiberion.swampmachine.gui.composer.transformers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import net.kiberion.audio.PlaySoundEvent;
import net.kiberion.swampmachine.api.invokables.Invokable;
import net.kiberion.swampmachine.factories.InvokablesFactory;
import net.kiberion.swampmachine.utils.SetUtils;

public class SoundTransformer extends AbstractTransformer<Map<String, Object>, Invokable> {

    public static final Set<String> TRANSFORMED_PARAMETER = SetUtils.buildSet("onClickSound");

    @Autowired
    private InvokablesFactory invokablesFactory;

    @Override
    public Invokable transformSingle(Map<String, Object> parameter, Map<String, Object> context) {
        ApplicationEvent event = new PlaySoundEvent (new Object(), (String) parameter.get("soundId"));
        return invokablesFactory.createEventInvokable(event);
    }

    @Override
    public Collection<String> getParameterNames() {
        return TRANSFORMED_PARAMETER;
    }

}
