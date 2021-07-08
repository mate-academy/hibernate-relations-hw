package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        Configuration configure = new Configuration();
        configure.configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }
}
