package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil() {}

    private static SessionFactory buildSessionFactory() {
        try{
            Configuration configuration = new Configuration().configure();
            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed" + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
