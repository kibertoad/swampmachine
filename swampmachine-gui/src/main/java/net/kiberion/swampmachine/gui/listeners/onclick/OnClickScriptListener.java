package net.kiberion.swampmachine.gui.listeners.onclick;

import org.apache.commons.lang3.Validate;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import net.kiberion.swampmachine.scripting.SwampBinding;
import net.kiberion.swampmachine.scripting.SwampScript;

public class OnClickScriptListener extends ChangeListener{

    private final SwampScript script;
    private final SwampBinding binding;
    
    public OnClickScriptListener(SwampScript script, SwampBinding binding) {
        Validate.notNull(script);
        Validate.notNull(binding);
        this.script = script;
        this.binding = binding;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        script.invoke(binding);                
    }
    
}
