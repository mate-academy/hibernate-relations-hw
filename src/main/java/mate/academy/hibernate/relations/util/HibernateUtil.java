package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory INSTANCE = createSessionFactoryInstance();

    public HibernateUtil() {
    }

    private static SessionFactory createSessionFactoryInstance() {
        return new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return INSTANCE;
    }
}
