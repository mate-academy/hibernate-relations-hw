package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory instance;

    private HibernateUtil() {
        instance = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            new HibernateUtil();
        }
        return instance;
    }
}
