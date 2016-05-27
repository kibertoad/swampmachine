package net.kiberion.persistence;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDao<T extends Serializable> extends HibernateDaoSupport{

    protected abstract Class<T> getEntityClass();
    
    @Transactional(readOnly=false)    
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Transactional(readOnly=false)
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Transactional(readOnly=false)
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Transactional(readOnly=true)
    public T findById(String id) {
        return getHibernateTemplate().get(getEntityClass(), id);
    }

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
}
