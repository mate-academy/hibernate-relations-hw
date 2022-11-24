package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory instance = initSessionFactory();

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }

    public static SessionFactory initSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
