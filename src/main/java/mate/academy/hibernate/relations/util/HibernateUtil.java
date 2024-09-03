package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory instance = initSessionFactory();

    public HibernateUtil() {
    }

    public static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error setting up Hibernate SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }
}
