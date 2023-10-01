package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static volatile SessionFactory factory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            synchronized (HibernateUtil.class) {
                if (factory == null) {
                    factory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return factory;
    }
}
