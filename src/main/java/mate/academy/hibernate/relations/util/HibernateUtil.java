package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory instance;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            try {
                instance = new Configuration().configure().buildSessionFactory();
            } catch (Exception e) {
                throw new RuntimeException("Error connect to DB", e);
            }
        }
        return instance;
    }
}
