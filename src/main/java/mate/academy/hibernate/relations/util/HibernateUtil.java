package mate.academy.hibernate.relations.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = init();

    private HibernateUtil() {
    }

    private static SessionFactory init() {
        try {
            return new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't init session factory.", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
