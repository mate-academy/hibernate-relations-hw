package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory SESSION_FACTORY;

    private HibernateUtil() {
    }

    private static void initSessionFactory(){
        final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SESSION_FACTORY = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (SESSION_FACTORY == null) {
            initSessionFactory();
        }
        return SESSION_FACTORY;
    }
}
