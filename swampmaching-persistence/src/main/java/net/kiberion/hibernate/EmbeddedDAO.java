package net.kiberion.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmbeddedDAO {

    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public <T> List<T> executeHQLQuery(String hql) {
        try {
            Session session = sessionFactory.openSession();
            //session.beginTransaction();
            Query q = session.createQuery(hql);
            return q.list();
            //session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            return null;
        }
    }

    public void save(Object object) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public <T> T load(Serializable entityID, Class<T> entityClass) {
        try {
            Session session = sessionFactory.openSession();
            return session.get(entityClass, entityID);
        } catch (HibernateException he) {
            he.printStackTrace();
            return null;
        }
    }

    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

}
