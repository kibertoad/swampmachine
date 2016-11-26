package net.kiberion.persistence.api;

import java.io.Serializable;

public interface CrudDao<T extends Serializable> {

    public Class<T> getEntityClass();

    /**
     * Persist the given transient instance.
     * 
     * @param entity
     */
    public void save(T entity);

    /**
     * Update the given persistent instance, associating it with the current
     * Session
     * 
     * @param entity
     */
    public void update(T entity);

    public void delete(T entity);

    public T findById(String id);

}
