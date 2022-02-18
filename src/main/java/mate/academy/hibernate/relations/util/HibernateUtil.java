package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    static {
        SESSION_FACTORY = new Configuration()
                .configure().
                buildSessionFactory();
    }

    private HibernateUtil() { }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
