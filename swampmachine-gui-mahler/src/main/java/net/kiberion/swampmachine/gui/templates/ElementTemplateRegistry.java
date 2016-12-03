package net.kiberion.swampmachine.gui.templates;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;

@ImmutableRegistry
public class ElementTemplateRegistry {

    @Getter
    private final Map<String, ElementTemplate> elementTemplates = new HashMap<>();
    
}
