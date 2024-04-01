package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory instance = getInstance();

    public static SessionFactory getSessionFactory() {
        return instance;
    }

    private static SessionFactory getInstance() {
        return new Configuration().configure().buildSessionFactory();
    }
}
