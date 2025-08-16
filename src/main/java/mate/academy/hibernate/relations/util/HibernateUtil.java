package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();
    private static final String SESSION_FACTORY_CREATING_FAILED = "Error creating Session Factory";

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException(SESSION_FACTORY_CREATING_FAILED, e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
