package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create SessionFactory", e);
        }
    }
}
