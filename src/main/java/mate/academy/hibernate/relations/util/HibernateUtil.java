package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory FACTORY = initFactory();

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }

    private static SessionFactory initFactory() {
        return new Configuration().configure().buildSessionFactory();

    }
}
