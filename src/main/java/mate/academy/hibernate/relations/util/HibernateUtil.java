package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
        super();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory initSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
