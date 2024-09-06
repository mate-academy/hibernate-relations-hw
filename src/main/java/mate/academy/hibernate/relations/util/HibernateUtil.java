package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Ran into an issue while configuring Hibernate ", e);
        }
    }

    private HibernateUtil() {

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
