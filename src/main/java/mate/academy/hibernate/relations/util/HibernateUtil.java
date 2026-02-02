package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Can't create a session", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }
}
