package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory factory = initFactory();

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    private static SessionFactory initFactory() {
        return new Configuration().configure().buildSessionFactory();

    }
}
