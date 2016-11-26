package net.kiberion.persistence.hibernate;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import net.kiberion.persistence.api.CrudDao;

/**
 * Hibernate-based abstract CRUD DAO
 * 
 * @author kibertoad
 *
 * @param <T>
 */
public abstract class AbstractHibernateDao<T extends Serializable> extends HibernateDaoSupport implements CrudDao<T> {

    @Override
    public abstract Class<T> getEntityClass();

    @Override
    @Transactional(readOnly = false)
    /**
     * Persist the given transient instance.
     * 
     * @param entity
     */
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    /**
     * Update the given persistent instance, associating it with the current
     * Hibernate {@link org.hibernate.Session}.
     * 
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(String id) {
        return getHibernateTemplate().get(getEntityClass(), id);
    }

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
