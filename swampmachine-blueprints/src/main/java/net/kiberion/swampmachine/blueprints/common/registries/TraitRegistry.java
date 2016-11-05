package net.kiberion.swampmachine.blueprints.common.registries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.blueprints.common.entities.api.Trait;

@ImmutableRegistry
public class TraitRegistry<T extends Trait> {

    @Getter
    private final Map<String, T> traits = new HashMap<>();

    public List<T> getTraitsWithTag(String tag) {
        return traits.values().parallelStream().filter((trait) -> {
            return trait.hasTag(tag);
        }).collect(Collectors.toList());
    }
}
