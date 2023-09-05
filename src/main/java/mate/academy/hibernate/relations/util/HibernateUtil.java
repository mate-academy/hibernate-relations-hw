package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        FACTORY = new Configuration().configure().buildSessionFactory();
    }

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
