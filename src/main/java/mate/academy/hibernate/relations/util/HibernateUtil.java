package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        initSessionFactory();
    }

    private HibernateUtil() {
    }

    public static void initSessionFactory() {
        try {
            var configure = new Configuration().configure();
            sessionFactory = configure.buildSessionFactory();
        } catch (Throwable ex) {
            throw new DataProcessingException("SessionFactory initialization failed", ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
