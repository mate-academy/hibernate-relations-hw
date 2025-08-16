package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory INSTANCE = getInstance();

    public static SessionFactory getSessionFactory() {
        return INSTANCE;
    }

    private static SessionFactory getInstance() {
        return new Configuration().configure().buildSessionFactory();
    }
}
