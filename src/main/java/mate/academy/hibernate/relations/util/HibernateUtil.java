package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory INSTANCE = initializeFactory();

    public static SessionFactory getSessionFactory() {
        return INSTANCE;
    }

    private static SessionFactory initializeFactory() {
        return new Configuration().configure().buildSessionFactory();

    }
}
