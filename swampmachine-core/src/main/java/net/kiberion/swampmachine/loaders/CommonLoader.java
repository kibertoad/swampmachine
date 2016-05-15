package net.kiberion.swampmachine.loaders;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

public class CommonLoader extends AbstractLoader{

    @Setter
    @Getter
    private String loadDirectory;
    
    @Setter
    @Getter
    private Class <? extends CommonModelEntityDescriptor> entityClass;
    
    @Setter
    private Map<String, ? extends CommonModelEntityDescriptor> targetMap = new HashMap<>();
    
    @Getter
    @Setter
    private String loadFileExtension;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends CommonModelEntityDescriptor> Map<String, T> getTargetMap() {
        return (Map<String, T>) targetMap;
    }
    
    
}
