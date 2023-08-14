package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = getSessionFactoryInstance();

    private HibernateUtil(){
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory getSessionFactoryInstance() {
        return new Configuration()
                .configure()
                .buildSessionFactory();
    }
}
