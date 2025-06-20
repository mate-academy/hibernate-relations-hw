package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError("Initial session factory is failed!: " + e);
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }
}
