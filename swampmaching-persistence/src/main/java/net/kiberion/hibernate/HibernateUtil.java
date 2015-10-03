package net.kiberion.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import net.kiberion.jpa.beans.Employee;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /*
     * private static SessionFactory buildSessionFactory() { try { // Create the
     * SessionFactory from hibernate.cfg.xml return new
     * Configuration().configure().buildSessionFactory( new
     * StandardServiceRegistryBuilder().build() ); } catch (Throwable ex) { //
     * Make sure you log the exception, as it might be swallowed
     * System.err.println("Initial SessionFactory creation failed." + ex); throw
     * new ExceptionInInitializerError(ex); } }
     */

    public static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        
        cfg.addAnnotatedClass(Employee.class);
        
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}