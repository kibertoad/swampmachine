package net.kiberion.swampmachine.loaders;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;
import net.kiberion.swampmachine.annotations.LoadOnStartup;
import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;

@LoadOnStartup
public class CommonSyncStartupLoader extends AbstractLoader implements SyncLoader {

    @Setter
    @Getter
    private String loadDirectory;

    @Setter
    @Getter
    private Class<? extends EntityModelDescriptor> entityClass;

    @Setter
    private Map<String, ? extends EntityModelDescriptor> targetMap = new HashMap<>();

    @Getter
    @Setter
    private String loadFileExtension;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends EntityModelDescriptor> Map<String, T> getTargetMap() {
        return (Map<String, T>) targetMap;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getLoadDirectory())
                .append(getLoadFileExtension())
                .append(getEntityClass())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommonSyncStartupLoader)) {
            return false;
        }
        CommonSyncStartupLoader comparedLoader = (CommonSyncStartupLoader) obj;

        return new EqualsBuilder()
                .append(getEntityClass(), comparedLoader.getEntityClass())
                .append(getLoadDirectory(), comparedLoader.getLoadDirectory())
                .append(getLoadFileExtension(), comparedLoader.getLoadFileExtension())
                .isEquals()
                && getTargetMap() == comparedLoader.getTargetMap();
    }
    
    @Override
    public String toString() {
        if (getEntityClass() != null) {
            return getEntityClass().getCanonicalName()+" loader";
        }
        return super.toString();
    }
}
